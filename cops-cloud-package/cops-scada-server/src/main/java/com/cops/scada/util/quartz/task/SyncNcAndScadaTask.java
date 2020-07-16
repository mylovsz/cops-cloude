package com.cops.scada.util.quartz.task;

import com.cops.entity.nc65.ProductionOrder;
import com.cops.library.until.DataConvert;
import com.cops.library.until.DateConvert;
import com.cops.library.until.ScadaPlanUtils;
import com.cops.scada.entity.Dict;
import com.cops.scada.entity.Plan;
import com.cops.scada.entity.PlanDay;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.entity.VO.PlanDayVO;
import com.cops.scada.enums.PlanDayExcelRowTypeEnum;
import com.cops.scada.service.*;
import com.cops.scada.util.DateUtil;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.util.ThreadContext;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 同步NC和Scada系统数据
 */
@Component("SyncNcAndScadaTask")
public class SyncNcAndScadaTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncNcAndScadaTask.class);

    @Value("${appDataTemp.path}")
    private String appDataTempPath;

    @Autowired
    PlanService planService;

    @Autowired
    ProductService productService;

    @Autowired
    SyncNCAndScadaService syncNCAndScadaService;

    @Autowired
    private SyncNcScadaUtil syncNcScadaUtil;

    @Value("${plan-day-disk.username}")
    private String uname;
    @Value("${plan-day-disk.password}")
    private String upwd;
    @Value("${plan-day-disk.domain}")
    private String domain;
    @Value("${plan-day-disk.url}")
    private String URL;
    @Value("${plan-day-disk.filename}")
    private String filename;

    // shiro 测试用例
    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;

    public void setUp() {
        ThreadContext.bind(securityManager);

    }
    // shiro 测试

    /**
     * 从NC同步订单状态到Scada中
     */
    public void NcToScadaForPlan(String param) {
        LOGGER.debug(LocalDateTime.now().toString() + " - param : " + param);
        List<Plan> planList = planService.getPlanForUndone();
        for (Plan plan :
                planList) {
            try {
                List<ProductionOrder> productionOrders = syncNCAndScadaService.getNCProductionOrderByPlan(plan);
                if (productionOrders != null && productionOrders.size() > 0) {
                    ProductionOrder productionOrder = (ProductionOrder) DataConvert.mapToObject((Map<String, Object>) productionOrders.get(0), ProductionOrder.class);
                    Product product = productService.selectById(plan.getProductId());
                    if(product != null
                            && product.getSn().equals(productionOrder.getMaterialCode())) {

                        if(StringUtils.isBlank(plan.getNcId())){

                            plan.setNcId(productionOrder.getPmoBillCode());
                            planService.updateById(plan);
                        }

                        if (StringUtils.isNotBlank(productionOrder.getActEndTime()))
                        {
                            plan.setEndDate(DateConvert.strToDateLong(productionOrder.getActEndTime()));
                            plan.setStartDate(DateConvert.strToDateLong(productionOrder.getActStartTime()));
                            plan.setState(2); // 已完成

                            planService.updateById(plan);
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void NcToScadaForPlanAll(String param){
        try {
            setUp();
            LOGGER.debug(LocalDateTime.now().toString() + " - param : " + param);

            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, uname, upwd);
            //主要利用类 SmbFile 去实现读取共享文件夹 shareFile 下的所有文件(文件夹)的名称
            SmbFile smbfile=new SmbFile(URL, auth);
            if(!smbfile.exists()){
                System.out.println("no such folder");
            }
            else{
                SmbFile[] files = smbfile.listFiles();
                for (SmbFile f : files) {
                    System.out.println(f.getName());
                    if(f.getName().equals(filename)){

                        Calendar calendar = Calendar.getInstance();

                        // 写入本地文件，然后读出进行解析，主要解决加密问题
                        String fileName = appDataTempPath
                                +"/play_day"
                                +(new java.text.SimpleDateFormat("yyyyMMdd").format(calendar.getTime()))
                                +".xlsx";

                        InputStream in = new BufferedInputStream(f.getInputStream());
                        File file = new File(fileName);
                        File fileParent = file.getParentFile();
                        if(!fileParent.exists()){
                            fileParent.mkdirs();
                        }
                        file.createNewFile();
                        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                        IOUtils.copy(in, out);
                        out.flush();
                        out.close();
                        file = new File(fileName);
                        MultipartFile multipartFile = new MockMultipartFile("copy"+file.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),new FileInputStream(file));
                        // End

                        syncNcScadaUtil.planForDay(f.getName(), multipartFile.getInputStream(), calendar);
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
