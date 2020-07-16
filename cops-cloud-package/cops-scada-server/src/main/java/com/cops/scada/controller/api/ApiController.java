package com.cops.scada.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cops.scada.base.BaseController;
import com.cops.scada.component.AsyncTask;
import com.cops.scada.entity.DTO.ExaminesA.ExaminesADTO;
import com.cops.scada.entity.DTO.ExaminesA.SiteDatasA;
import com.cops.scada.entity.DTO.ExaminesB.ExaminesBDTO;
import com.cops.scada.entity.DTO.ExaminesB.SiteDatasB;
import com.cops.scada.entity.DTO.ExaminesC.AgingDatasC;
import com.cops.scada.entity.DTO.ExaminesC.ExaminesCDTO;
import com.cops.scada.entity.DTO.ExaminesC.Models.ExperienceLED;
import com.cops.scada.entity.DTO.ExaminesC.SiteDatasC;
import com.cops.scada.entity.DTO.ExaminesD.ExaminesDDTO;
import com.cops.scada.entity.DTO.ExaminesD.SiteDatasD;
import com.cops.scada.entity.DTO.ExaminesE.ExaminesEDTO;
import com.cops.scada.entity.DTO.ExaminesE.SiteDatas;
import com.cops.scada.entity.DTO.ExaminesE.SiteInfo;
import com.cops.scada.entity.DTO.ExaminesF.ExaminesFDTO;
import com.cops.scada.entity.DTO.ExaminesF.SiteDatasF;
import com.cops.scada.entity.DTO.ExaminesG.ExaminesGDTO;
import com.cops.scada.entity.DTO.ExaminesG.SiteDatasG;
import com.cops.scada.entity.DTO.ExaminesH.ExaminesHDTO;
import com.cops.scada.entity.DTO.ExaminesH.SiteDatasH;
import com.cops.scada.entity.DTO.ExaminesK.ExaminesKDTO;
import com.cops.scada.entity.DTO.ExaminesK.SiteDataK;
import com.cops.scada.entity.*;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.service.*;
import com.cops.scada.service.impl.MemaryCacheServiceImpl;
import com.cops.scada.util.DateUtil;
import com.cops.scada.util.RestResponse;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ProduceService produceService;

    @Autowired
    private ExaminesAService examinesAService;

    @Autowired
    private ExaminesBService examinesBService;

    @Autowired
    private ExaminesCService examinesCService;

    @Autowired
    private ExaminesCExperienceService examinesCExperienceService;

    @Autowired
    private ExaminesDService examinesDService;

    @Autowired
    private ExaminesEService examinesEService;

    @Autowired
    private ExaminesFService examinesFService;

    @Autowired
    private ExaminesGService examinesGService;

    @Autowired
    private FactorySiteService factorySiteService;

    @Autowired
    private LackProduceService lackProduceService;

    @Autowired
    private PlanDayService planDayService;

    @Autowired
    MemaryCacheServiceImpl memaryCacheService;

    @Autowired
    private AsyncTask asyncTask;

    /**
     * 设备工作状态信息，保存到redis
     */
    public void DeviceWorkInfo(SiteInfo SiteInfo) {
        memaryCacheService.set(SiteInfo.getSiteWorkers(), SiteInfo);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public RestResponse test(@RequestBody String content) {
        LOGGER.info(content);
        return RestResponse.success();
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/factorySite", method = RequestMethod.GET)
    public RestResponse factorySite() {
        List<FactorySite> pageData = factorySiteService.getListForTree();
        return RestResponse.success().setData(pageData);
    }

    /**
     * 计算产品流程是否存在问题
     *
     * @param produce
     */
    private void checkProduceFlow(Produce produce, SiteInfo siteInfo, Date collectDate) {
        LackProduce lackProduce;

        Integer lackResultCount = 0;
        String lackResultSite = "";

        if (collectDate == null)
            collectDate = new Date();

        //终检测试
        if (produce.getResultF() == 1) {
            // 终检漏测
            if (produce.getResultA() != 1) {
                lackResultCount++;
                lackResultSite += "[初检耐压] ";
            }
            if (produce.getResultB() != 1) {
                lackResultCount++;
                lackResultSite += "[初检测试] ";
            }
            if (produce.getResultC() != 1) {
                lackResultCount++;
                lackResultSite += "[老化测试] ";
            }
            if (produce.getResultD() != 1) {
                lackResultCount++;
                lackResultSite += "[终检耐压] ";
            }
            if (produce.getResultE() != 1) {
                lackResultCount++;
                lackResultSite += "[点火测试] ";
            }
            if (lackResultCount > 0) {
                lackProduce = new LackProduce();
                lackProduce.setProduceId(produce.getId()); // 产品编码
                lackProduce.setType(0); // 0-默认类型 1-工艺类型
                lackProduce.setCurrentSite("终检测试");
                lackProduce.setLackSite(lackResultSite);
                lackProduce.setLackNum(new BigDecimal(lackResultCount));
                lackProduce.setSiteWorker(siteInfo.getSiteWorkers());
                lackProduce.setSiteInfo(siteInfo.toString());
                lackProduce.setState(0); // 未处理
                lackProduce.setCollectDate(collectDate);
                lackProduceService.addProduce(lackProduce);
                return;
            }
        }

        //点火测试
        if (produce.getResultE() == 1) {
            // 点火测试
            if (produce.getResultA() != 1) {
                lackResultCount++;
                lackResultSite += "[初检耐压] ";
            }
            if (produce.getResultB() != 1) {
                lackResultCount++;
                lackResultSite += "[初检测试] ";
            }
            if (produce.getResultC() != 1) {
                lackResultCount++;
                lackResultSite += "[老化测试] ";
            }
            if (produce.getResultD() != 1) {
                lackResultCount++;
                lackResultSite += "[终检耐压] ";
            }
            if (lackResultCount > 0) {
                lackProduce = new LackProduce();
                lackProduce.setProduceId(produce.getId()); // 产品编码
                lackProduce.setType(0); // 0-默认类型 1-工艺类型
                lackProduce.setCurrentSite("点火测试");
                lackProduce.setLackSite(lackResultSite);
                lackProduce.setLackNum(new BigDecimal(lackResultCount));
                lackProduce.setSiteWorker(siteInfo.getSiteWorkers());
                lackProduce.setSiteInfo(siteInfo.toString());
                lackProduce.setState(0); // 未处理
                lackProduce.setCollectDate(collectDate);
                lackProduceService.addProduce(lackProduce);
                return;
            }
        }

        //终检耐压
        if (produce.getResultD() == 1) {
            // 终检耐压
            if (produce.getResultA() != 1) {
                lackResultCount++;
                lackResultSite += "[初检耐压] ";
            }
            if (produce.getResultB() != 1) {
                lackResultCount++;
                lackResultSite += "[初检测试] ";
            }
            if (produce.getResultC() != 1) {
                lackResultCount++;
                lackResultSite += "[老化测试] ";
            }
            if (lackResultCount > 0) {
                lackProduce = new LackProduce();
                lackProduce.setProduceId(produce.getId()); // 产品编码
                lackProduce.setType(0); // 0-默认类型 1-工艺类型
                lackProduce.setCurrentSite("终检耐压");
                lackProduce.setLackSite(lackResultSite);
                lackProduce.setLackNum(new BigDecimal(lackResultCount));
                lackProduce.setSiteWorker(siteInfo.getSiteWorkers());
                lackProduce.setSiteInfo(siteInfo.toString());
                lackProduce.setState(0); // 未处理
                lackProduce.setCollectDate(collectDate);
                lackProduceService.addProduce(lackProduce);
                return;
            }
        }

        //老化测试
        if (produce.getResultC() == 1) {
            // 老化测试
            if (produce.getResultA() != 1) {
                lackResultCount++;
                lackResultSite += "[初检耐压] ";
            }
            if (produce.getResultB() != 1) {
                lackResultCount++;
                lackResultSite += "[初检测试] ";
            }
            if (lackResultCount > 0) {
                lackProduce = new LackProduce();
                lackProduce.setProduceId(produce.getId()); // 产品编码
                lackProduce.setType(0); // 0-默认类型 1-工艺类型
                lackProduce.setCurrentSite("老化测试");
                lackProduce.setLackSite(lackResultSite);
                lackProduce.setLackNum(new BigDecimal(lackResultCount));
                lackProduce.setSiteWorker(siteInfo.getSiteWorkers());
                lackProduce.setSiteInfo(siteInfo.toString());
                lackProduce.setState(0); // 未处理
                lackProduce.setCollectDate(collectDate);
                lackProduceService.addProduce(lackProduce);
                return;
            }
        }

        //初检测试
        if (produce.getResultB() == 1) {
            // 初检测试
            if (produce.getResultA() != 1) {
                lackResultCount++;
                lackResultSite += "[初检耐压] ";
            }
            if (lackResultCount > 0) {
                lackProduce = new LackProduce();
                lackProduce.setProduceId(produce.getId()); // 产品编码
                lackProduce.setType(0); // 0-默认类型 1-工艺类型
                lackProduce.setCurrentSite("初检测试");
                lackProduce.setLackSite(lackResultSite);
                lackProduce.setLackNum(new BigDecimal(lackResultCount));
                lackProduce.setSiteWorker(siteInfo.getSiteWorkers());
                lackProduce.setSiteInfo(siteInfo.toString());
                lackProduce.setState(0); // 未处理
                lackProduce.setCollectDate(collectDate);
                lackProduceService.addProduce(lackProduce);
                return;
            }
        }
    }

    private void statisticsPlanDayVO(PlanDayVO planDayVO, Integer result) {
        planDayVO.setNum(planDayVO.getNum().add(new BigDecimal(1)));
        if (result == 1) {
            // 合格品加一
            planDayVO.setNumSuccess(planDayVO.getNumSuccess().add(new BigDecimal(1)));
        }
        if (planDayVO.getState() == -1) {
            planDayVO.setState(0);
        }

        LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
                + " 生产数量：" + planDayVO.getNum()
                + " 合格数量：" + planDayVO.getNumSuccess());

        planDayService.updatePlanDayVO(planDayVO);
    }

    /**
     * 统计产品数据
     *
     * @param produce 新数据入库前的记录
     * @param type    "B"-初检  "F"-终检
     * @param date
     * @param result  1-通过 2-失败
     */
    private void statisticsProduce(Produce produce, String type, Date date, Integer result) {
        List<PlanDayVO> planDayVOList;
        switch (type) {
            case "A":
                // 初检耐压---组装进度
                planDayVOList = planDayService.getPlanDayVOForDay(date, produce.getPlanId(), "JOB0002");
                if (planDayVOList != null && planDayVOList.size() > 0) {
                    PlanDayVO planDayVO = planDayVOList.get(0);

                    // 制造数量加一 由人工来报
                    // planDayVO.setManufactureNum(planDayVO.getManufactureNum().add(new BigDecimal(1)));

                    ExaminesA examinesA = examinesAService.getExaminesAByProduceId(produce.getId(), date);
                    if (examinesA == null) {
                        statisticsPlanDayVO(planDayVO, result);
                    }
                }
                break;
            case "B":
                // 初检数据---组装进度
                planDayVOList = planDayService.getPlanDayVOForDay(date, produce.getPlanId(), "JOB0002");
                if (planDayVOList != null && planDayVOList.size() > 0) {
                    PlanDayVO planDayVO = planDayVOList.get(0);

                    // 制造数量加一 由人工来报
                    // planDayVO.setManufactureNum(planDayVO.getManufactureNum().add(new BigDecimal(1)));

                    ExaminesB examinesB = examinesBService.getExaminesBByProduceId(produce.getId(), date);
                    if (examinesB == null) {
                        statisticsPlanDayVO(planDayVO, result);
                    }
                    // 下面为采用Redis缓存的方案，不能解决处理之前的数据，所有废弃
//                    PlanDayVO planDayVO = planDayVOList.get(0);
//
//                    LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
//                            + " 原生产数量：" + planDayVO.getNum()
//                            + " 原合格数量：" + planDayVO.getNumSuccess()
//                            + " 产品SN：" + produce.getSn()
//                            + " 采集时间：" + DateUtil.Format(date)
//                            + " 采集类型：" + type
//                            + " 采集结果：" + result
//                            + " 记录状态：" + produce.getResultB());
//
//                    // 制造数量加一 由人工来报
//                    // planDayVO.setManufactureNum(planDayVO.getManufactureNum().add(new BigDecimal(1)));
//                    Date d = new Date();
//                    long timeout = (DateUtil.getEndTime(d).getTime() - d.getTime()) / 1000;
//
//                    boolean isExaminesB = examinesBService.hasExaminesBForCache(produce.getId());
//
//                    LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
//                            + " 已存在记录"
//                            + " 是否在缓存："+isExaminesB);
//
//                    if (isExaminesB == false) {
//                        // 不是今天的，应该添加一条记录
//                        // 因为redis可能存在多人访问，所以返回值必须大于0，才代表真实需要添加
//                        Long l = examinesBService.addExaminesBToCache(produce.getId(), timeout);
//
//                        LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
//                                + " 加入缓存结果："+l);
//
//                        if (l > 0) {
//                            statisticsPlanDayVO(planDayVO, result);
//                        }
//                    }
                }
                break;
            case "D":
                // 初检耐压---组装进度
                planDayVOList = planDayService.getPlanDayVOForDay(date, produce.getPlanId(), "JOB0003");
                if (planDayVOList != null && planDayVOList.size() > 0) {
                    PlanDayVO planDayVO = planDayVOList.get(0);

                    // 制造数量加一 由人工来报
                    // planDayVO.setManufactureNum(planDayVO.getManufactureNum().add(new BigDecimal(1)));

                    ExaminesD examinesD = examinesDService.getExaminesDByProduceId(produce.getId(), date);
                    if (examinesD == null) {
                        statisticsPlanDayVO(planDayVO, result);
                    }
                }
                break;
            case "F":
                // 终检数据---终检包装
                planDayVOList = planDayService.getPlanDayVOForDay(date, produce.getPlanId(), "JOB0003");
                if (planDayVOList != null && planDayVOList.size() > 0) {
                    PlanDayVO planDayVO = planDayVOList.get(0);

                    // 制造数量加一 由人工来报
                    // planDayVO.setManufactureNum(planDayVO.getManufactureNum().add(new BigDecimal(1)));

                    ExaminesF examinesF = examinesFService.getExaminesFByProduceId(produce.getId(), date);

                    if (examinesF == null) {
                        statisticsPlanDayVO(planDayVO, result);
                    }
                    // 下面为采用Redis缓存的方案，不能解决处理之前的数据，所有废弃
//                    PlanDayVO planDayVO = planDayVOList.get(0);
//
//                    LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
//                            + " 原生产数量：" + planDayVO.getNum()
//                            + " 原合格数量：" + planDayVO.getNumSuccess()
//                            + " 产品SN：" + produce.getSn()
//                            + " 采集时间：" + DateUtil.Format(date)
//                            + " 采集类型：" + type
//                            + " 采集结果：" + result
//                            + " 记录状态：" + produce.getResultB());
//
//                    // 制造数量加一 由人工来报
//                    // planDayVO.setManufactureNum(planDayVO.getManufactureNum().add(new BigDecimal(1)));
//                    Date d = new Date();
//                    long timeout = (DateUtil.getEndTime(d).getTime() - d.getTime()) / 1000;
//
//                    boolean isExaminesF = examinesFService.hasExaminesFForCache(produce.getId());
//
//                    LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
//                            + " 已存在记录"
//                            + " 是否在缓存："+isExaminesF);
//
//                    if (isExaminesF == false) {
//                        // 时间不是今天的，应该添加一条记录
//                        // 因为redis可能存在多人访问，所以返回值必须大于0，才代表真实需要添加
//
//                        Long l = examinesFService.addExaminesFToCache(produce.getId(), timeout);
//                        LOGGER.info("PlanDay => " + planDayVO.getPlanSn()
//                                + " 加入缓存结果："+l);
//
//                        if (l > 0) {
//                            statisticsPlanDayVO(planDayVO, result);
//                        }
//                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 根据产品的状态，生产总状态
     * prduce result
     * 0-待生产
     * 1-通过
     * 2-不通过
     * <p>
     * state  0-待投产
     * 1-投产中
     * 2-已完成
     * 3-取消
     * 4-故障
     * 5-维修中
     * 6-已维修
     * 7-维修且正常
     * 8-报废
     *
     * @param produce
     */
    private void updateProduce(Produce produce, SiteInfo siteInfo, Date collectDate) {
        if (produce.getResultA() == 2 || produce.getResultB() == 2
                || produce.getResultC() == 2 || produce.getResultD() == 2
                || produce.getResultE() == 2 || produce.getResultF() == 2
                || produce.getResultG() == 2) {
            produce.setState(4); // 故障
        } else if (produce.getResultA() == 1 && produce.getResultB() == 1
                && produce.getResultC() == 1 && produce.getResultD() == 1
                && produce.getResultE() == 1 && produce.getResultF() == 1
                && produce.getResultG() == 1) {
            switch (produce.getState()) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    produce.setState(7); // 维修且正常
                    break;
                default:
                    produce.setState(2); // 已完成
                    break;
            }
        } else {
            produce.setState(1); // 投产中
        }

        // 计算测试流程是否存在问题
        checkProduceFlow(produce, siteInfo, collectDate);
    }

    /**
     * a 初检耐压数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesa", method = RequestMethod.POST)
    public RestResponse apiExaminesA(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            ExaminesADTO examinesDTO = JSON.parseObject(content, ExaminesADTO.class);
            if (examinesDTO != null) {
                List<SiteDatasA> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesA> list = new ArrayList<ExaminesA>();
                    for (SiteDatasA sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesA examinesA = new ExaminesA();
                            examinesA.setProduceId(produce.getId());
                            examinesA.setCollectDate(sd.getDateTime());
                            examinesA.setMeterA(sd.getStep().toUpperCase());  // 对应步骤
                            examinesA.setMeterB(sd.getType().toUpperCase());  // 对应类型
                            examinesA.setMeterC(sd.getMeter1().toUpperCase());
                            examinesA.setMeterD(sd.getMeter2().toUpperCase());
                            examinesA.setMeterE(sd.getMeter3().toUpperCase());
                            examinesA.setMeterF(sd.getMeter4().toUpperCase());
                            examinesA.setResult(sd.getResult().toUpperCase());

                            if ("1".equals(examinesA.getMeterA())) {
                                statisticsProduce(produce
                                        , "A"
                                        , examinesA.getCollectDate()
                                        , sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            }

                            list.add(examinesA);

                            produce.setResultA(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesA.getUpdateDate());
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesA.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0) {
                        LOGGER.info("[处理数据结束]" + content);
                        return RestResponse.failure("不存在该型号");
                    }
                    examinesAService.insertBatch(list);
                }
                LOGGER.info("[处理数据结束]" + content);
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }

    /**
     * b 初检数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesb", method = RequestMethod.POST)
    public RestResponse apiExaminesB(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            ExaminesBDTO examinesDTO = JSON.parseObject(content, ExaminesBDTO.class);
            if (examinesDTO != null) {
                List<SiteDatasB> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesB> list = new ArrayList<ExaminesB>();
                    for (SiteDatasB sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesB examinesB = new ExaminesB();
                            examinesB.setProduceId(produce.getId());
                            examinesB.setCollectDate(sd.getDateTime());
                            examinesB.setMeterA(sd.getGNDResult().toUpperCase());  // 对应步骤
                            examinesB.setMeterB(sd.getACWResult().toUpperCase());  // 对应类型
                            examinesB.setMeterC(sd.getVoltage().toUpperCase());
                            examinesB.setMeterD(sd.getPowerFactor().toUpperCase());
                            examinesB.setMeterE(sd.getPower1().toUpperCase());
                            examinesB.setMeterF(sd.getPower2().toUpperCase());
                            examinesB.setMeterG(sd.getPower3().toUpperCase());
                            examinesB.setMeterH(sd.getPower4().toUpperCase());
                            examinesB.setResult(sd.getResult().toUpperCase());

                            // 必须放在加入数据库之前加入下面的代码

                            // wanglm 组装数据修改为以初检耐压为准
//                            statisticsProduce(produce
//                                    , "B"
//                                    , examinesB.getCollectDate()
//                                    , sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            // wanglm end

                            list.add(examinesB);

                            produce.setResultB(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesB.getUpdateDate());
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesB.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0) {
                        LOGGER.info("[处理数据结束]" + content);
                        return RestResponse.failure("不存在该型号");
                    }
                    examinesBService.insertBatch(list);
                }
                LOGGER.info("[处理数据结束]" + content);
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }

    /**
     * c 老化数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesc", method = RequestMethod.POST)
    public RestResponse apiExaminesC(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            JSONObject jsonObject = JSONObject.parseObject(content);
            if (jsonObject.containsKey("SiteInfo")) {
                ExaminesCDTO examinesDTO = JSON.parseObject(content, ExaminesCDTO.class);
                if (examinesDTO != null) {
                    List<SiteDatasC> siteDatas = examinesDTO.getSiteDatas();

                    DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                    if (siteDatas != null && siteDatas.size() > 0) {
                        ArrayList<ExaminesC> list = new ArrayList<ExaminesC>();
                        for (SiteDatasC sd :
                                siteDatas) {
                            String sn = sd.getBarcode();
                            if (sn == null || sn.length() == 0)
                                continue;
                            Produce produce = produceService.getOneBySN(sn);
                            if (produce != null) {
                                // 说明存在此型号，此时开始填写数据，到C表中
                                List<AgingDatasC> agingDatasCS = sd.getAgingDatas();
                                if (agingDatasCS != null && agingDatasCS.size() > 0) {
                                    Integer isOK = 1, i = 0;
                                    Date updateDate = null;
                                    for (AgingDatasC a :
                                            agingDatasCS) {
                                        ExaminesC examinesC = new ExaminesC();
                                        examinesC.setProduceId(produce.getId());
                                        examinesC.setCollectDate(a.getDateTime());
                                        examinesC.setMeterA(a.getTemperature().toUpperCase());  // 对应步骤
                                        examinesC.setMeterB(a.getVoltageMax().toUpperCase());  // 对应类型
                                        examinesC.setMeterC(a.getVoltage().toUpperCase());
                                        examinesC.setMeterD(a.getVoltageMin().toUpperCase());
                                        examinesC.setMeterE(a.getPowerMax().toUpperCase());
                                        examinesC.setMeterF(a.getPower().toUpperCase());
                                        examinesC.setMeterG(a.getPowerMin().toUpperCase());
                                        examinesC.setMeterH(a.getPFMax().toUpperCase());
                                        examinesC.setMeterI(a.getPF().toUpperCase());
                                        examinesC.setMeterJ(a.getPFMin().toUpperCase());
                                        examinesC.setMeterK(a.getCurrentMax().toUpperCase());
                                        examinesC.setMeterL(a.getCurrent().toUpperCase());
                                        examinesC.setMeterM(a.getCurrentMin().toUpperCase());
                                        examinesC.setMeterN(i.toString());
                                        i++;
                                        examinesC.setResult(a.getResult().toUpperCase());
                                        if (!a.getResult().equalsIgnoreCase("PASS")) {
                                            isOK = 2;
                                        }
                                        if (updateDate == null)
                                            updateDate = examinesC.getUpdateDate();
                                        list.add(examinesC);
                                    }
                                    produce.setResultC(isOK);
                                    //produce.setUpdateDate(updateDate);
                                    updateProduce(produce, examinesDTO.getSiteInfo(), updateDate);
                                    produceService.saveProduce(produce);
                                }
                            }
                        }
                        if (list.size() == 0) {
                            LOGGER.info("[处理数据结束]" + content);
                            return RestResponse.failure("不存在该型号");
                        }
                        examinesCService.insertBatch(list);
                    }
                    LOGGER.info("[处理数据结束]" + content);
                    return RestResponse.success();
                }
            } else if (jsonObject.containsKey("upload_type")) {
                switch (jsonObject.getString("upload_type")) {
                    case "led_examines_c":
                        //判断是否存在value
                        if (jsonObject.containsKey("value")) {
                            //转换为实体
                            ExperienceLED experienceLED = JSON.parseObject(jsonObject.getString("value"), ExperienceLED.class);//JSON.toJavaObject(jsonObject, ExperienceLED.class);
                            if (experienceLED != null) {
                                EntityWrapper<ExaminesCExperience> wrapper = new EntityWrapper<>();
                                String meterA = jsonObject.getString("name").replace(".xls", "");
                                wrapper.eq("meter_a", meterA);
                                if (examinesCExperienceService.selectCount(wrapper) > 0) {
                                    return RestResponse.failure("处理失败，重复提交");
                                }
                                if (Integer.parseInt(experienceLED.getTotal()) == 0) {
                                    return RestResponse.failure("异常数据，不处理");
                                }
                                ExaminesCExperience examinesCExperience = new ExaminesCExperience();
                                examinesCExperience.setMeterA(meterA);
                                if (experienceLED.getCreateTime().toLowerCase().indexOf("by") != -1) {
                                    examinesCExperience.setMeterB(experienceLED.getCreateTime().toLowerCase().substring(0, experienceLED.getCreateTime().toLowerCase().indexOf("by") - 1));
                                } else {
                                    examinesCExperience.setMeterB(experienceLED.getCreateTime());
                                }

                                examinesCExperience.setMeterC(experienceLED.getPass());
                                examinesCExperience.setMeterD(experienceLED.getFailedPercent());
                                Plan plan = new Plan();
                                if (StringUtils.isNotBlank(experienceLED.getOrderNumber())) {
                                    examinesCExperience.setMeterE(experienceLED.getOrderNumber());
                                    EntityWrapper<Plan> planEntityWrapper = new EntityWrapper<>();
                                    planEntityWrapper.eq("sn", experienceLED.getOrderNumber());
                                    plan = planService.selectOne(planEntityWrapper);
                                    if (plan == null) {
                                        return RestResponse.failure("异常数据，不处理");
                                    }
                                } else {
                                    List<ExperienceLED.DataListBean> lst = experienceLED.getDataList().stream().filter(t -> t.getDeviceNumber() != null && t.getDeviceNumber().length() > 0).collect(Collectors.toList());
                                    if (lst.size() > 0) {
                                        String sn = lst.get(0).getDeviceNumber();
                                        Produce produce = produceService.getOneBySN(sn);
                                        if (produce != null) {
                                            plan.setId(produce.getPlanId());
                                            plan = planService.selectById(plan);
                                            if (plan != null) {
                                                examinesCExperience.setMeterE(plan.getSn());
                                            } else {
                                                return RestResponse.failure("无匹配数据，不处理");
                                            }
                                        } else {
                                            return RestResponse.failure("无匹配数据，不处理");
                                        }
                                    } else {
                                        return RestResponse.failure("无匹配数据，不处理");
                                    }
                                    lst.clear();
                                }
                                examinesCExperience.setMeterF(experienceLED.getFailed());
                                examinesCExperience.setMeterG(experienceLED.getTotal());
                                examinesCExperience.setMeterH(experienceLED.getDeviceModel());
                                String[] pattern = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM",
                                        "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
                                        "yyyyMMddHHmmss",
                                        "yyyy-MM-dd HH:mm:ss",
                                        "yyyy/MM/dd HH:mm:ss"};
                                Date createDate = DateUtils.parseDate(examinesCExperience.getMeterB(), pattern);
                                if (StringUtils.isNotBlank(examinesCExperience.getMeterE())) {
                                    examinesCExperienceService.insert(examinesCExperience);
                                }
                                long currentTimeMillis = System.currentTimeMillis();
                                if (experienceLED.getDataList() != null && experienceLED.getDataList().size() > 0) {
                                    asyncTask.ExaminesCInsertAsync(examinesCExperience, experienceLED, plan, createDate);
                                }
                                long currentTimeMillis1 = System.currentTimeMillis();
                                return RestResponse.success("处理成功,"+"task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
                            }
                        }

                        break;
                    default:
                        return RestResponse.failure("数据格式错误");
                }
            }
        } catch (Exception ex) {
            LOGGER.error("apiExaminesC", ex);
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }


    /**
     * d 终检耐压数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesd", method = RequestMethod.POST)
    public RestResponse apiExaminesD(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            ExaminesDDTO examinesDTO = JSON.parseObject(content, ExaminesDDTO.class);
            if (examinesDTO != null) {
                List<SiteDatasD> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesD> list = new ArrayList<ExaminesD>();
                    for (SiteDatasD sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesD examinesD = new ExaminesD();
                            examinesD.setProduceId(produce.getId());
                            examinesD.setCollectDate(sd.getDateTime());
                            examinesD.setMeterA(sd.getStep().toUpperCase());  // 对应步骤
                            examinesD.setMeterB(sd.getType().toUpperCase());  // 对应类型
                            examinesD.setMeterC(sd.getMeter1().toUpperCase());
                            examinesD.setMeterD(sd.getMeter2().toUpperCase());
                            examinesD.setMeterE(sd.getMeter3().toUpperCase());
                            examinesD.setMeterF(sd.getMeter4().toUpperCase());
                            examinesD.setResult(sd.getResult().toUpperCase());

                            if ("1".equals(examinesD.getMeterA())) {
                                statisticsProduce(produce
                                        , "D"
                                        , examinesD.getCollectDate()
                                        , sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            }
                            list.add(examinesD);

                            produce.setResultD(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesD.getUpdateDate());
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesD.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0) {
                        LOGGER.info("[处理数据结束]" + content);
                        return RestResponse.failure("不存在该型号");
                    }
                    examinesDService.insertBatch(list);
                }
                LOGGER.info("[处理数据结束]" + content);
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }

    /**
     * e 点火数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinese", method = RequestMethod.POST)
    public RestResponse apiExaminesE(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            ExaminesEDTO examinesEDTO = JSON.parseObject(content, ExaminesEDTO.class);
            if (examinesEDTO != null) {
                List<SiteDatas> siteDatas = examinesEDTO.getSiteDatas();

                DeviceWorkInfo(examinesEDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesE> list = new ArrayList<ExaminesE>();
                    for (SiteDatas sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesE examinesE = new ExaminesE();
                            examinesE.setProduceId(produce.getId());
                            examinesE.setCollectDate(sd.getDateTime());
                            examinesE.setMeterA(sd.getVolMax().toUpperCase().replace("V", ""));
                            examinesE.setMeterB(sd.getVolValue().toUpperCase().replace("V", ""));
                            examinesE.setMeterC(sd.getVolMin().toUpperCase().replace("V", ""));
                            examinesE.setMeterD(sd.getTimeMax().toUpperCase().replace("MS", ""));
                            examinesE.setMeterE(sd.getTimeValue().toUpperCase().replace("MS", ""));
                            examinesE.setMeterF(sd.getTimeMin().toUpperCase().replace("MS", ""));
                            examinesE.setResult(sd.getResult().toUpperCase());
                            list.add(examinesE);

                            produce.setResultE(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesE.getUpdateDate());
                            updateProduce(produce, examinesEDTO.getSiteInfo(), examinesE.getCollectDate());

                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0) {
                        LOGGER.info("[处理数据结束]" + content);
                        return RestResponse.failure("不存在该型号");
                    }
                    examinesEService.insertBatch(list);
                }
                LOGGER.info("[处理数据结束]" + content);
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }

    /**
     * f 终检数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesf", method = RequestMethod.POST)
    public RestResponse apiExaminesF(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            ExaminesFDTO examinesDTO = JSON.parseObject(content, ExaminesFDTO.class);
            if (examinesDTO != null) {
                List<SiteDatasF> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesF> list = new ArrayList<ExaminesF>();
                    for (SiteDatasF sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesF examinesF = new ExaminesF();
                            examinesF.setProduceId(produce.getId());
                            examinesF.setCollectDate(sd.getDateTime());
                            examinesF.setMeterA(sd.getGNDResult().toUpperCase());  // 对应步骤
                            examinesF.setMeterB(sd.getACWResult().toUpperCase());  // 对应类型
                            examinesF.setMeterC(sd.getVoltage().toUpperCase());
                            examinesF.setMeterD(sd.getPowerFactor().toUpperCase());
                            examinesF.setMeterE(sd.getPower1().toUpperCase());
                            examinesF.setMeterF(sd.getPower2().toUpperCase());
                            examinesF.setMeterG(sd.getPower3().toUpperCase());
                            examinesF.setMeterH(sd.getPower4().toUpperCase());
                            examinesF.setResult(sd.getResult().toUpperCase());

                            // 必须放在加入数据库之前加入下面的代码
//                            statisticsProduce(produce
//                                    , "F"
//                                    , examinesF.getCollectDate()
//                                    , sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);

                            list.add(examinesF);

                            produce.setResultF(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesF.getUpdateDate());
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesF.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0) {
                        LOGGER.info("[处理数据结束]" + content);
                        return RestResponse.failure("不存在该型号");
                    }
                    examinesFService.insertBatch(list);
                }
                LOGGER.info("[处理数据结束]" + content);
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }

    @RequestMapping(value = "/examinesg", method = RequestMethod.POST)
    public RestResponse apiExaminesG(@RequestBody String content) {
        LOGGER.info(content);
        try {
            ExaminesGDTO examinesDTO = JSON.parseObject(content, ExaminesGDTO.class);
            if (examinesDTO != null) {
                List<SiteDatasG> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesG> list = new ArrayList<ExaminesG>();
                    for (SiteDatasG sd :
                            siteDatas) {
                        String sn = sd.getInternalNumber();
                        String customeSN = sd.getExternalNumber();
                        if (sn == null || sn.length() == 0 || customeSN == null || customeSN.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesG examinesG = new ExaminesG();
                            examinesG.setProduceId(produce.getId());
                            examinesG.setCollectDate(sd.getCreatDateTime());
                            examinesG.setMeterA(sn);  // 内部编码
                            examinesG.setMeterB(customeSN);  // 客户编码
                            examinesG.setMeterC(DateUtil.Format(sd.getUpdateDateTime()));
                            examinesG.setResult("PASS");
                            list.add(examinesG);

                            produce.setCustomerSn(customeSN);
                            produce.setResultG(1); // G包装成功
                            if (sd.getCreatDateTime() != null) {
                                produce.setRemarks(DateUtil.Format(sd.getCreatDateTime()));
                            }
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesG.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0)
                        return RestResponse.failure("不存在该型号");
                    examinesGService.insertBatch(list);
                }
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        return RestResponse.failure("数据格式错误");
    }

    /**
     * h LED老化数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesh", method = RequestMethod.POST)
    public RestResponse apiExaminesH(@RequestBody String content) {
        LOGGER.debug(content);
        try {
            ExaminesHDTO examinesDTO = JSON.parseObject(content, ExaminesHDTO.class);
            if (examinesDTO != null) {
                List<SiteDatasH> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesC> list = new ArrayList<ExaminesC>();
                    for (SiteDatasH sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesC examinesC = new ExaminesC();
                            examinesC.setProduceId(produce.getId());
                            examinesC.setCollectDate(sd.getCreatedDate());
                            examinesC.setMeterA(sd.getFailTime().toUpperCase());
                            examinesC.setResult(sd.getResult().toUpperCase());

                            list.add(examinesC);

                            produce.setResultC(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesF.getUpdateDate());
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesC.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0)
                        return RestResponse.failure("不存在该型号");
                    examinesCService.insertBatch(list);
                }
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        return RestResponse.failure("数据格式错误");
    }


    /**
     * k LED终检数据接口
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/examinesk", method = RequestMethod.POST)
    public RestResponse apiExaminesK(@RequestBody String content) {
        LOGGER.info("[处理数据开始]" + content);
        try {
            ExaminesKDTO examinesDTO = JSON.parseObject(content, ExaminesKDTO.class);
            if (examinesDTO != null) {
                List<SiteDataK> siteDatas = examinesDTO.getSiteDatas();

                DeviceWorkInfo(examinesDTO.getSiteInfo());//缓存设备状态信息

                if (siteDatas != null && siteDatas.size() > 0) {
                    ArrayList<ExaminesK> list = new ArrayList<ExaminesK>();
                    for (SiteDataK sd :
                            siteDatas) {
                        String sn = sd.getBarcode();
                        if (sn == null || sn.length() == 0)
                            continue;
                        Produce produce = produceService.getOneBySN(sn);
                        if (produce != null) {
                            // 说明存在此型号，此时开始填写数据，到E表中
                            ExaminesK examinesK = new ExaminesK();
                            examinesK.setProduceId(produce.getId());
                            examinesK.setCollectDate(sd.getDateTime());
                            examinesK.setMeterA(sd.getResult().toUpperCase());  // 对应步骤
                            examinesK.setMeterB(sd.getDateTime().toString());  // 对应类型
                            examinesK.setMeterC(sd.getTestItem().toUpperCase());
                            examinesK.setMeterD(sd.getItemName().toUpperCase());
                            examinesK.setMeterE(sd.getUSL().toUpperCase());
                            examinesK.setMeterF(sd.getLSL().toUpperCase());
                            examinesK.setMeterG(sd.getValue().toUpperCase());

                            // 必须放在加入数据库之前加入下面的代码
//                            statisticsProduce(produce
//                                    , "F"
//                                    , examinesF.getCollectDate()
//                                    , sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);

                            list.add(examinesK);

                            produce.setResultF(sd.getResult().equalsIgnoreCase("PASS") ? 1 : 2);
                            //produce.setUpdateDate(examinesF.getUpdateDate());
                            updateProduce(produce, examinesDTO.getSiteInfo(), examinesK.getCollectDate());
                            produceService.saveProduce(produce);
                        }
                    }
                    if (list.size() == 0) {
                        LOGGER.info("[处理数据结束]" + content);
                        return RestResponse.failure("不存在该型号");
                    }
                    //examinesFService.insertBatch(list);
                }
                LOGGER.info("[处理数据结束]" + content);
                return RestResponse.success();
            }
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
        }
        LOGGER.info("[处理数据结束]" + content);
        return RestResponse.failure("数据格式错误");
    }

    @RequestMapping(value = "/uploadCustom", method = RequestMethod.POST)
    public RestResponse uploadCustom(@RequestParam(value = "file") MultipartFile[] files, HttpSession session) {
        if (files.length == 0) {
            return RestResponse.failure("请选择文件");
        }
        ArrayList<String> results = new ArrayList<String>();
        for (MultipartFile file : files) {
            // 遍历文件
            if (file != null) {
                try {
                    int validCount = 0;
                    String fileName = file.getOriginalFilename();
                    if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
                        return RestResponse.failure("上传文件格式不正确，请选择excel文件");
                    }
                    boolean isExcel2003 = true;
                    if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
                        isExcel2003 = false;
                    }
                    InputStream is = file.getInputStream();
                    Workbook wb = null;
                    if (isExcel2003) {
                        wb = new HSSFWorkbook(is);
                    } else {
                        wb = new XSSFWorkbook(is);
                    }

                    for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                        // 遍历工作簿
                        Sheet sheet = wb.getSheetAt(sheetIndex);
                        if (sheet == null) {
                            continue;
                        }
                        ArrayList<ExaminesG> examinesGS = new ArrayList<>();
                        for (int r = 2; r <= sheet.getLastRowNum(); r++) {
                            try {
                                Row row = sheet.getRow(r);
                                if (row == null) {
                                    continue;
                                }

                                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                                String sn = row.getCell(0).getStringCellValue();
                                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                                String customerSN = row.getCell(1).getStringCellValue();
                                Date createDate = null;
                                try {
                                    createDate = row.getCell(2).getDateCellValue();
                                } catch (Exception e1) {
                                    //e1.printStackTrace();
                                    LOGGER.info(fileName + "第" + (r + 1) + "行，不存在日期");
                                }

                                if (StringUtils.isEmpty(sn) || StringUtils.isEmpty(customerSN)) {
                                    continue;
                                }

                                // 检索数据库
                                Produce produce = produceService.getOneBySN(sn);
                                if (produce != null) {
                                    produce.setCustomerSn(customerSN);
                                    produce.setResultG(1); // G包装成功
                                    if (createDate != null) {
                                        produce.setRemarks(DateUtil.Format(createDate));
                                    }
                                    SiteInfo siteInfo = new SiteInfo();
                                    siteInfo.setSiteDataType("包装");
                                    siteInfo.setSiteIP("127.0.0.1");
                                    siteInfo.setSiteLocation("Excel");
                                    siteInfo.setSiteWorkers(fileName);
                                    updateProduce(produce, siteInfo, createDate);
                                    produceService.saveProduce(produce);

                                    // 有效条数加一
                                    validCount++;
                                    ExaminesG examinesG = new ExaminesG();
                                    examinesG.setMeterA(sn);
                                    examinesG.setMeterB(customerSN);
                                    examinesG.setCollectDate(createDate);
                                    examinesG.setProduceId(produce.getId());
                                    examinesG.setResult("PASS");
                                    examinesGS.add(examinesG);
                                }
                            } catch (Exception ex) {
                                //ex.printStackTrace();
                                LOGGER.info(fileName + "第" + (r + 1) + "行，SN码数据存在问题");
                            }
                        }
                        if (examinesGS.size() > 0) {
                            examinesGService.insertOrUpdateBatch(examinesGS);
                        }
                    }
                    results.add(String.format("%s 数量：%d", fileName, validCount));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        }
        String r = "";
        for (String s : results) {
            r += s + "\r\n";
        }
        return RestResponse.success(r).setData(results);
    }
}
