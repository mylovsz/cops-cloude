package com.cops.scada;

import com.cops.entity.nc65.DTO.SaleOrderPageWrapperDTO;
import com.cops.entity.nc65.ProductionOrder;
import com.cops.entity.nc65.SaleOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.LayerData;
import com.cops.library.until.NC65RestResponse;
import com.cops.library.until.ScadaPlanUtils;
import com.cops.scada.controller.ProductController;
import com.cops.scada.entity.*;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.enums.JobSnEnum;
import com.cops.scada.enums.PlanDayExcelRowTypeEnum;
import com.cops.scada.service.*;
import com.cops.scada.util.DateUtil;
import com.cops.scada.util.RestResponse;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ThreadContext;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.*;

@RunWith(SpringRunner.class)
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootTest
public class NC65Tests {

    private static final Logger LOGGER = LoggerFactory.getLogger(NC65Tests.class);

    // shiro 测试用例
    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;

    @Before
    public void setUp() throws Exception {
        ThreadContext.bind(securityManager);

    }
    // shiro 测试

    @Autowired
    private PlanService planService;

    @Autowired
    private NC65Service nc65Service;

    @Autowired
    private ProductService productService;

    @Autowired
    private PlanDayService planDayService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DictService dictService;

//    void PlanForDayData(PlanDayExcelRowTypeEnum rowType, Row row, Date date){
//        if(rowType == PlanDayExcelRowTypeEnum.None){
//            return;
//        }
//
//        String ncSn = row.getCell(2).getStringCellValue().trim();
//        if(StringUtils.isBlank(ncSn))
//            return;
//        String ncType = row.getCell(4).getStringCellValue().trim();
//        Double ncNum = row.getCell(6).getNumericCellValue();
//        Double finishNum = row.getCell(7).getNumericCellValue();
//        Double dayNum = row.getCell(8).getNumericCellValue();
//
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
//                && ncNum.equals(Double.parseDouble(productionOrder.getNum())))){
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
//                || product.getType() == 4){
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
//
//        // 添加每日计划
//        List<PlanDayVO> planDayVOList = planDayService.getPlanDayVOForDay(date, plan.getId(), rowType.getJobSn());
//        if(planDayVOList == null || planDayVOList.size() == 0){
//            // 说明不存在，需要动态添加
//            // 获取对应的工序
//            JobVO jobVO = jobService.getJobVOBy(plan.getId(), rowType.getJobSn());
//            if(jobVO == null) {
//                LOGGER.info("未找到工序");
//                return;
//            }
//            PlanDay planDay = new PlanDay();
//            planDay.setPlanId(plan.getId());
//            planDay.setPlanNum(new BigDecimal(dayNum));
//            planDay.setNum(new BigDecimal(0));
//            planDay.setNumFinish(new BigDecimal(finishNum));
//            planDay.setNumSuccess(new BigDecimal(0));
//            planDay.setNumScrap(new BigDecimal(0));
//            planDay.setNumRepair(new BigDecimal(0));
//            planDay.setManufactureDate(date);
//            planDay.setManufactureNum(new BigDecimal(0));
//            planDay.setManufactureStaffs("");
//            planDay.setWorkTime(new BigDecimal(0));
//            planDay.setWorkOvertime(new BigDecimal(0));
//            planDay.setWorkErrortime(new BigDecimal(0));
//            // 获取全局品质要求
//            Dict dict = dictService.getDictByTypeLabel("scada_quality_tag", "全局品质目标");
//            planDay.setTagQuality(dict == null ? "95" : dict.getValue());
//            planDay.setManufactureTimes(new BigDecimal(0));
//            planDay.setJobId(jobVO.getId());
//            planDay.setState(-1); // 加工中
//            planDayService.addPlanDay(planDay);
//        }
//    }
//
//    void PlanForDay(String fileName, InputStream fileInputStream, Calendar calendar){
//        try {
//            int validCount = 0;
//            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
//                LOGGER.info("上传文件格式不正确，请选择excel文件");
//                return;
//            }
//
//            boolean isExcel2003 = true;
//            if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
//                isExcel2003 = false;
//            }
//
//            Workbook wb = null;
//            if (isExcel2003) {
//                wb = new HSSFWorkbook(fileInputStream);
//            } else {
//                wb = new XSSFWorkbook(fileInputStream);
//            }
//
//
//
//            for (int sheetIndex = wb.getNumberOfSheets()-1; sheetIndex >= 0; sheetIndex--) {
//                // 遍历工作簿
//                Sheet sheet = wb.getSheetAt(sheetIndex);
//                if (sheet == null) {
//                    continue;
//                }
//
//                String sheetName = sheet.getSheetName();
//                String[] sheetNameDate = sheetName.split("-");
//
//                if(Integer.parseInt(sheetNameDate[0]) == (calendar.get(Calendar.MONTH)+1)
//                    && Integer.parseInt(sheetNameDate[1]) == calendar.get(Calendar.DAY_OF_MONTH)){
//                    // 找到当天的数据，开始同步其内的内容
//                    PlanDayExcelRowTypeEnum rowType = PlanDayExcelRowTypeEnum.None;
//                    for (int r = 2; r <= sheet.getLastRowNum(); r++) {
//                        try {
//                            Row row = sheet.getRow(r);
//                            if (row == null) {
//                                continue;
//                            }
//
//                            if(row.getCell(1).getStringCellValue().equals("插件")){
//                                // 所有的插件工单
//                                rowType = PlanDayExcelRowTypeEnum.PlugIn;
//                            }
//
//                            if(row.getCell(1).getStringCellValue().equals("组装")){
//                                // 所有的插件工单
//                                rowType = PlanDayExcelRowTypeEnum.Composing;
//                            }
//
//                            if(row.getCell(1).getStringCellValue().equals("终检包装")){
//                                // 所有的插件工单
//                                rowType = PlanDayExcelRowTypeEnum.Package;
//                            }
//
//                            if(row.getCell(1).getStringCellValue().equals("辅料成型")){
//                                // 所有的插件工单
//                                rowType = PlanDayExcelRowTypeEnum.Subsidiary;
//                            }
//
//                            PlanForDayData(rowType, row, calendar.getTime());
//
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                            LOGGER.info(fileName + "第" + (r + 1) + "行，SN码数据存在问题");
//                        }
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    @Test
    public void smbTest(){
//        try {
//            String ip = "192.168.1.160";
//            String uname = "LLSCR4003";
//            String upwd = "Lumlux123";
//            String domain = "lumlux";
//            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, uname, upwd);
//            //主要利用类 SmbFile 去实现读取共享文件夹 shareFile 下的所有文件(文件夹)的名称
//            String URL="smb://"+ip+"/个人资料/LLSCR4003/";
//            SmbFile smbfile=new SmbFile(URL, auth);
//            if(!smbfile.exists()){
//                System.out.println("no such folder");
//            }
//            else{
//                SmbFile[] files = smbfile.listFiles();
//                for (SmbFile f : files) {
//                    System.out.println(f.getName());
//                    if(f.getName().equals("HID-生产日计划.xlsx")){
//                        Calendar calendar = Calendar.getInstance();
//                        PlanForDay(f.getName(), f.getInputStream(), calendar);
//                    }
//                }
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    @Autowired
//    NC65SaleOrderService nc65SaleOrderService;
//
//    @Test
//    public void SaleOrderTest(){
//        SaleOrderPageWrapperDTO saleOrderPageWrapperDTO = new SaleOrderPageWrapperDTO();
//        //saleOrderPageWrapperDTO.setPage(1);
//        saleOrderPageWrapperDTO.setLimit(10);
//        for(int i=0;i<10;i++) {
//            Date date = new Date();
//            saleOrderPageWrapperDTO.setPage(i+1);
//            NC65RestResponse nc65RestResponse = nc65SaleOrderService.getSaleOrder(saleOrderPageWrapperDTO);//存在超时问题
//            LayerData<SaleOrder> page = (LayerData<SaleOrder>)DataConvert.mapToObject((Map<String, Object>) nc65RestResponse.getData(), LayerData.class);
//            System.out.println("测试次数："+(i+1)+" 读取数据条数："+nc65RestResponse.size()+" 耗时："+(new Date().getTime()-date.getTime()));
//        }
//    }
}
