package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Job;
import com.cops.scada.entity.Plan;
import com.cops.scada.dao.PlanDao;
import com.cops.scada.entity.Produce;
import com.cops.scada.entity.Product;
import com.cops.scada.entity.VO.PlanProductVO;
import com.cops.scada.entity.VO.PlanQualityDetailVO;
import com.cops.scada.entity.VO.StatisticsPlanVO;
import com.cops.scada.service.JobService;
import com.cops.scada.service.PlanService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.scada.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 生产计划 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-03-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanServiceImpl extends ServiceImpl<PlanDao, Plan> implements PlanService {

    @Autowired
    JobService jobService;

    @Autowired
    ProduceService produceService;

    @Override
    public PlanQualityDetailVO getPlanQualityDetailVO(Long planId) {
        return this.baseMapper.getPlanQualityDetailVO(planId);
    }

    @Override
    public Boolean existsBySN(String sn) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("sn", sn);
        return baseMapper.exists(wrapper);
    }

    @Override
    public Plan getPlanByNcSn(String ncSn) {
        Plan plan = new Plan();
        plan.setNcId(ncSn);
        return baseMapper.selectOne(plan);
    }

    @Override
    public Boolean existsBySN(String sn, Long id) {
        EntityWrapper<Product> wrapper = new EntityWrapper<>();
        wrapper.eq("sn", sn);
        wrapper.ne("id", id);
        return baseMapper.exists(wrapper);
    }

    @Override
    public Boolean addPlan(Plan plan) {
        this.baseMapper.insert(plan);
        // 增加默认工序（默认将所有的全局工序加入）
        List<Job> jobList = jobService.getJobTemplate();
        for (Job job :
                jobList) {
            job.setPlanId(plan.getId());

            // 0-全局模板
            // 1-产品类型模板
            // 2-生产计划
            job.setIdentityType(2);
            job.setParentId(job.getId());
            job.setParentIds(job.getId()+",");
            job.setId(null); // 采用数据库自增
            jobService.addJob(job);
        }
        return null;
    }

    @Override
    public Integer going(Plan plan) {
        // 根据标签，生成生成产品记录
        Long quantity = plan.getQuantity();
        String sn = plan.getLableRange().substring(0, plan.getLableRange().length()-5);
        int d = Integer.parseInt(plan.getLableRange().substring(plan.getLableRange().length()-5));
        List<Produce> list = new ArrayList<>();
        String existsSN;
        for (int i = d; i <= (quantity+d-1); i++) {
            existsSN = sn + String.format("%05d", i);

            // 如果存在，则跳过此条记录
            if(produceService.existsBySN(existsSN))
                continue;

            Produce produce = new Produce();
            produce.setPlanId(plan.getId());
            produce.setSn(existsSN);
            produce.setState(0);
            produce.setResultA(0);
            produce.setResultB(0);
            produce.setResultC(0);
            produce.setResultD(0);
            produce.setResultE(0);
            produce.setResultF(0);
            produce.setResultG(0);
            produce.setResultH(0);
            produce.setResultI(0);
            produce.setResultL(0);
            list.add(produce);
        }
        Integer successCount = 0;
        if(list.size()>0){
            produceService.insertBatch(list);
            successCount = list.size();
        }
        return successCount;
    }

    @Override
    public Plan getPlanByProduct(Long productId) {
        Plan plan = new Plan();
        plan.setProductId(productId);
        return this.baseMapper.selectOne(plan);
    }

    @Override
    public Plan getPlanBySN(String sn) {
        Plan plan = new Plan();
        plan.setSn(sn);
        return this.baseMapper.selectOne(plan);
    }

    @Override
    public List<Plan> getAllPlan() {
        EntityWrapper<Plan> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        wrapper.orderBy("sn");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Plan> getPlanForUndone() {
        EntityWrapper<Plan> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false)
                .ne("state", 2);
        wrapper.orderBy("sn");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public StatisticsPlanVO getStatisticsPlanVO() {
        EntityWrapper<StatisticsPlanVO> wrapper = new EntityWrapper<>();
        wrapper.eq("del_flag", false);
        return this.baseMapper.getStatisticsPlanVO(wrapper);
    }

    @Override
    public StatisticsPlanVO getStatisticsPlanVO(Integer productType, Date startDate, Date endDate) {
        EntityWrapper<StatisticsPlanVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("product.type", productType);
        wrapper.between("plan.tag_start_date", startDate, endDate);
        return this.baseMapper.getStatisticsPlanVOAndProduct(wrapper);
    }

    @Override
    public StatisticsPlanVO getStatisticsHidPlanVO() {
        EntityWrapper<StatisticsPlanVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("product.type", 0);
        return this.baseMapper.getStatisticsPlanVOAndProduct(wrapper);
    }

    @Override
    public StatisticsPlanVO getStatisticsLedPlanVO() {
        EntityWrapper<StatisticsPlanVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("product.type", 2);
        return this.baseMapper.getStatisticsPlanVOAndProduct(wrapper);
    }

    @Override
    public Page<PlanProductVO> getPlanProduct(Page<PlanProductVO> page, EntityWrapper<PlanProductVO> wrapper) {
        return page.setRecords(this.baseMapper.getPlanProduct(page, wrapper));
    }
}
