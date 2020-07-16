package com.cops.scada.util.quartz.task;

import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.ScadaPlanUtils;
import com.cops.scada.entity.*;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.enums.PlanDayExcelRowTypeEnum;
import com.cops.scada.service.*;
import com.cops.scada.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("SyncNcScadaUtil")
public class SyncNcScadaUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncNcScadaUtil.class);

    @Autowired
    PlanService planService;

    @Autowired
    ProductService productService;

    @Autowired
    SyncNCAndScadaService syncNCAndScadaService;

    @Autowired
    private NC65Service nc65Service;

    @Autowired
    private PlanDayService planDayService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DictService dictService;

    @Autowired
    private PlanLinkService planLinkService;

    public Plan planGetOrCreate(String ncSn, boolean isMaster) {
        // 查询是否存在
        Plan plan = planService.getPlanByNcSn(ncSn);
        if (plan == null) {
            // 说明不存在该计划，需要添加计划，并初始化相应的信息
            // 首先去NC系统查询详细的信息
            ProductionOrder productionOrder = nc65Service.getProductionOrderByNcSn(ncSn);
            if (productionOrder == null) {
                return null;
            }
            Product product = productService.getOneBySn(productionOrder.getMaterialCode());
            if (product == null) {
                // 说明不存在该型号，需要添加
                product = new Product();
                product.setSn(productionOrder.getMaterialCode());
                product.setBom(productionOrder.getMaterialCode());
                product.setIntroduction(productionOrder.getMaterialCode());
                product.setName(productionOrder.getMaterialName());
                product.setState(0); // 在售
                product.setRemarks("由NC同步代码，自动生成");
                switch (productionOrder.getMaterialCode().substring(0, 2)) {
                    case "LA":
                        product.setType(3);
                        break;
                    case "LC":
                        product.setType(1);
                        break;
                    case "LD":
                        product.setType(2);
                        break;
                    case "LF":
                        product.setType(4);
                        break;
                    case "LG":
                        product.setType(5);
                        break;
                    case "LM":
                        product.setType(6);
                        break;
                    case "LP":
                        product.setType(7);
                        break;
                    case "LR":
                        product.setType(8);
                        break;
                    case "LS":
                        product.setType(9);
                        break;
                    case "LT":
                        product.setType(0);
                        break;
                    default:
                        // 半成品
                        product.setType(10);
                        break;
                }
                product = productService.saveProduct(product);
                if (product == null) {
                    LOGGER.info("NC自动添加型号失败");
                    return null;
                }
            }

            // 判断数据的有效性
            // ncSn 因为在nc中查询到，所以肯定正确
//            if(!(ncType.equals(productionOrder.getMaterialCode())
//                    && ncNum.equals(Double.parseDouble(productionOrder.getNum())))){
//                // Excel数据填写错误
//                LOGGER.info("Excel 填写错误，请咨询核对 : "+ncSn);
//                // 发送报警邮件
//
//                // 待定
//            }

            // 生成Plan计划
            plan = new Plan();
            plan.setNcId(ncSn);
            // 55A22019071700002421 截取规则 天+最后三位码
            plan.setSn(ncSn.substring(6, 12) + ncSn.substring(17, 20));
            plan.setProductId(product.getId());
            plan.setQuantity(Long.parseLong(productionOrder.getNum()));
            plan.setLableRange(ScadaPlanUtils.getPlanLable(plan.getSn(), product.getType()));
            plan.setTagStartDate(DateUtil.parse(productionOrder.getPlanStartTime()));
            plan.setTagEndDate(DateUtil.parse(productionOrder.getPlanEndTime()));
            plan.setStartDate(DateUtil.parse(productionOrder.getActStartTime()));
            plan.setEndDate(DateUtil.parse(productionOrder.getActEndTime()));

            plan.setType(isMaster ? 0 : 1);
            plan.setRule(Integer.parseInt(productionOrder.getMoBillType()) + 1);

            if ((isMaster && StringUtils.isNotBlank(plan.getLableRange()))
                    || product.getType() == 0  // LT = 0 || LF = 4
                    || product.getType() == 4) {
                plan.setState(1); // 投产中
                planService.addPlan(plan);
                // 自动投产
                try {
                    planService.going(plan);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    LOGGER.info("投产失败:" + plan.getSn());
                }
            } else {
                plan.setState(0); // 待投产
                planService.addPlan(plan);
            }

        }

        return plan;
    }

    public void planForDayData(PlanDayExcelRowTypeEnum rowType, Row row, Date date) {
        if (rowType == PlanDayExcelRowTypeEnum.None) {
            return;
        }

        /**
         * 工单号
         */
        String ncSn = row.getCell(2).getStringCellValue().trim();
        if (StringUtils.isBlank(ncSn)) {
            return;
        }
        /**
         * 产品型号
         */
        String ncType = row.getCell(4).getStringCellValue().trim();
        /**
         * 工单数量
         */
        Double ncNum = row.getCell(6).getNumericCellValue();
        /**
         * 已完成数量
         */
        Double finishNum = row.getCell(7).getNumericCellValue();
        /**
         * 今日计划量
         */
        Double dayNum = row.getCell(8).getNumericCellValue();
        /**
         * 主计划工单号
         */
        String masterNcSn = row.getCell(9).getStringCellValue();

        Plan plan = null;

        if (StringUtils.isNotBlank(masterNcSn)) {
            // 说明存在主型号，先找到主型号
            Plan masterPlan = planGetOrCreate(masterNcSn, true);
            plan = planGetOrCreate(ncSn, false);

            if (masterPlan == null || plan == null) {
                return;
            }
            if (planLinkService.getPlanLinkSlaveVO(masterPlan.getId(), plan.getId()) == null) {
                // 加入关联
                PlanLink planLink = new PlanLink();
                planLink.setMasterPlanId(masterPlan.getId());
                planLink.setSlavePlanId(plan.getId());
                if (!planLinkService.insert(planLink)) {
                    return;
                }
            }
        } else {
            //
            plan = planGetOrCreate(ncSn, true);
        }

        if (plan == null) {
            return;
        }

//        Plan plan = planService.getPlanByNcSn(ncSn);
//        if(plan == null){
//            // 说明不存在该计划，需要添加计划，并初始化相应的信息
//            // 首先去NC系统查询详细的信息
//            ProductionOrder productionOrder = nc65Service.getProductionOrderByNcSn(ncSn);
//            if(productionOrder == null)
//                return;
//            Product product = productService.getOneBySn(productionOrder.getMaterialCode());
//            if (product == null){
//                // 说明不存在该型号，需要添加
//                product = new Product();
//                product.setSn(productionOrder.getMaterialCode());
//                product.setBom(productionOrder.getMaterialCode());
//                product.setIntroduction(productionOrder.getMaterialCode());
//                product.setName(productionOrder.getMaterialName());
//                product.setState(0); // 在售
//                product.setRemarks("由NC同步代码，自动生成");
//                switch (productionOrder.getMaterialCode().substring(0, 2)) {
//                    case "LA":
//                        product.setType(3);
//                        break;
//                    case "LC":
//                        product.setType(1);
//                        break;
//                    case "LD":
//                        product.setType(2);
//                        break;
//                    case "LF":
//                        product.setType(4);
//                        break;
//                    case "LG":
//                        product.setType(5);
//                        break;
//                    case "LM":
//                        product.setType(6);
//                        break;
//                    case "LP":
//                        product.setType(7);
//                        break;
//                    case "LR":
//                        product.setType(8);
//                        break;
//                    case "LS":
//                        product.setType(9);
//                        break;
//                    case "LT":
//                        product.setType(0);
//                        break;
//                    default:
//                        // 半成品
//                        product.setType(10);
//                        break;
//                }
//                product = productService.saveProduct(product);
//                if (product == null) {
//                    LOGGER.info("NC自动添加型号失败");
//                    return;
//                }
//            }
//
//            // 判断数据的有效性
//            // ncSn 因为在nc中查询到，所以肯定正确
//            if(!(ncType.equals(productionOrder.getMaterialCode())
//                    && ncNum.equals(Double.parseDouble(productionOrder.getNum())))){
//                // Excel数据填写错误
//                LOGGER.info("Excel 填写错误，请咨询核对 : "+ncSn);
//                // 发送报警邮件
//
//                // 待定
//            }
//
//            // 生成Plan计划
//            plan = new Plan();
//            plan.setNcId(ncSn);
//            // 55A22019071700002421 截取规则 天+最后三位码
//            plan.setSn(ncSn.substring(10, 12)+ncSn.substring(17, 20));
//            plan.setProductId(product.getId());
//            plan.setQuantity(Long.parseLong(productionOrder.getNum()));
//            plan.setLableRange(ScadaPlanUtils.getPlanLable(plan.getSn(), product.getType()));
//            plan.setTagStartDate(DateUtil.parse(productionOrder.getPlanStartTime()));
//            plan.setTagEndDate(DateUtil.parse(productionOrder.getPlanEndTime()));
//            plan.setStartDate(DateUtil.parse(productionOrder.getActStartTime()));
//            plan.setEndDate(DateUtil.parse(productionOrder.getActEndTime()));
//
//            if(product.getType() == 0  // LT = 0 || LF = 4
//                    || product.getType() == 4){
//                plan.setState(1); // 投产中
//                planService.addPlan(plan);
//                // 自动投产
//                try {
//                    planService.going(plan);
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                    LOGGER.info("投产失败:"+plan.getSn());
//                }
//            }
//            else{
//                plan.setState(0); // 待投产
//                planService.addPlan(plan);
//            }
//
//        }

        // 添加每日计划
        List<PlanDayVO> planDayVOList = planDayService.getPlanDayVOForDay(date, plan.getId(), rowType.getJobSn());
        if (planDayVOList == null || planDayVOList.size() == 0) {
            // 说明不存在，需要动态添加
            // 获取对应的工序
            JobVO jobVO = jobService.getJobVOBy(plan.getId(), rowType.getJobSn());
            if (jobVO == null) {
                LOGGER.info("未找到工序");
                return;
            }
            PlanDay planDay = new PlanDay();
            planDay.setPlanId(plan.getId());
            planDay.setPlanNum(new BigDecimal(dayNum));
            planDay.setNum(new BigDecimal(0));
            Integer finish = planDayService.getPlanDayForMakeNum(plan.getId(), null);
            if (finish == null) finish = 0;
            planDay.setNumFinish(new BigDecimal(finish));
            planDay.setNumSuccess(new BigDecimal(0));
            planDay.setNumScrap(new BigDecimal(0));
            planDay.setNumRepair(new BigDecimal(0));
            planDay.setManufactureDate(date);
            planDay.setManufactureNum(new BigDecimal(0));
            planDay.setManufactureStaffs("");
            planDay.setWorkTime(new BigDecimal(0));
            planDay.setWorkOvertime(new BigDecimal(0));
            planDay.setWorkErrortime(new BigDecimal(0));
            // 获取全局品质要求
            Dict dict = dictService.getDictByTypeLabel("scada_quality_tag", "全局品质目标");
            planDay.setTagQuality(dict == null ? "95" : dict.getValue());
            planDay.setManufactureTimes(new BigDecimal(0));
            planDay.setJobId(jobVO.getId());
            if (rowType == PlanDayExcelRowTypeEnum.Subsidiary
                    || rowType == PlanDayExcelRowTypeEnum.PlugIn) {
                planDay.setState(4); // 挂起
            } else {
                planDay.setState(-1); // 代加工
            }
            planDayService.addPlanDay(planDay);
        }
    }

    public void planForDay(String fileName, InputStream fileInputStream, Calendar calendar) {
        try {
            int validCount = 0;
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                LOGGER.info("上传文件格式不正确，请选择excel文件");
                return;
            }

            boolean isExcel2003 = true;
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                isExcel2003 = false;
            }

            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(fileInputStream);
            } else {
                wb = new XSSFWorkbook(fileInputStream);
            }


            for (int sheetIndex = wb.getNumberOfSheets() - 1; sheetIndex >= 0; sheetIndex--) {
                // 遍历工作簿
                Sheet sheet = wb.getSheetAt(sheetIndex);
                if (sheet == null) {
                    continue;
                }

                String sheetName = sheet.getSheetName();
                String[] sheetNameDate = sheetName.split("-");

                if (Integer.parseInt(sheetNameDate[0].trim()) == (calendar.get(Calendar.MONTH) + 1)
                        && Integer.parseInt(sheetNameDate[1].trim()) == calendar.get(Calendar.DAY_OF_MONTH)) {
                    // 找到当天的数据，开始同步其内的内容
                    PlanDayExcelRowTypeEnum rowType = PlanDayExcelRowTypeEnum.None;
                    for (int r = 2; r <= sheet.getLastRowNum(); r++) {
                        try {
                            Row row = sheet.getRow(r);
                            if (row == null) {
                                continue;
                            }

                            if (row.getCell(1).getStringCellValue().equals("插件")) {
                                // 所有的插件工单
                                rowType = PlanDayExcelRowTypeEnum.PlugIn;
                            }

                            if (row.getCell(1).getStringCellValue().equals("组装")) {
                                // 所有的插件工单
                                rowType = PlanDayExcelRowTypeEnum.Composing;
                            }

                            if (row.getCell(1).getStringCellValue().equals("终检包装")) {
                                // 所有的插件工单
                                rowType = PlanDayExcelRowTypeEnum.Package;
                            }

                            if (row.getCell(1).getStringCellValue().equals("辅料成型")) {
                                // 所有的插件工单
                                rowType = PlanDayExcelRowTypeEnum.Subsidiary;
                            }

                            planForDayData(rowType, row, calendar.getTime());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            LOGGER.info(fileName + "第" + (r + 1) + "行，SN码数据存在问题");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
