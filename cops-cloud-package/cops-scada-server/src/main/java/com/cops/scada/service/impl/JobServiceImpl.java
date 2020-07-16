package com.cops.scada.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Job;
import com.cops.scada.dao.JobDao;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.service.JobService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 作业（工艺）管理 服务实现类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl extends ServiceImpl<JobDao, Job> implements JobService {

    @Override
    public List<Job> getJobTemplate() {
        EntityWrapper<Job> wrapper = new EntityWrapper<>();
        wrapper.eq("identity_type", 0);
        wrapper.eq("del_flag", 0);
        wrapper.orderBy("sort", true);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public JobVO getJobVOBy(Long planId, String jobSn) {
        EntityWrapper<JobVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("plan.id", planId);
        wrapper.eq("job.sn", jobSn);
        List<JobVO> jobVOS = this.baseMapper.getJobVOByPlanIdAJobSN(wrapper);
        if(jobVOS != null && jobVOS.size()>0){
            return jobVOS.get(0);
        }
        return null;
    }

    @Override
    public JobVO getJobVOBy(String planSn, String jobSn) {
        EntityWrapper<JobVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("plan.sn", planSn);
        wrapper.eq("job.sn", jobSn);
        List<JobVO> jobVOS = this.baseMapper.getJobVOByPlanIdAJobSN(wrapper);
        if(jobVOS != null && jobVOS.size()>0){
            return jobVOS.get(0);
        }
        return null;
    }

    @Override
    public List<JobVO> getJobVOByPlanId(Long planId) {
        EntityWrapper<JobVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("plan.id", planId);
        wrapper.orderBy("plan.sn",true);
        wrapper.orderBy("job.sort", true);
        List<JobVO> jobVOS = this.baseMapper.getJobVOByPlanIdAJobSN(wrapper);
        if(jobVOS != null && jobVOS.size()>0){
            return jobVOS;
        }
        return null;
    }

    @Override
    public JobVO getJobVOById(Long id) {
        EntityWrapper<JobVO> wrapper = new EntityWrapper<>();
        wrapper.eq("plan.del_flag", false);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("job.id", id);
        List<JobVO> jobVOS = this.baseMapper.getJobVOByPlanIdAJobSN(wrapper);
        if(jobVOS != null && jobVOS.size()>0){
            return jobVOS.get(0);
        }
        return null;
    }

    @Override
    public Boolean addJob(Job job) {
        return this.insert(job);
    }

    @Override
    public Page<JobVO> getPageJobVO(Page<JobVO> page, EntityWrapper<JobVO> wrapper) {
        wrapper.orderBy("plan.sn",true);
        wrapper.orderBy("job.sort", true);
        return page.setRecords(this.baseMapper.getPageJobVO(page, wrapper));
    }

    @Override
    public List<Job> getJobTemplateByProductId(Long productId) {
        EntityWrapper<Job> wrapper = new EntityWrapper<>();
        wrapper.eq("job.identity_type", 1);
        wrapper.eq("job.type", 2);
        wrapper.eq("job.del_flag", false);
        wrapper.eq("job.product_id", productId);
        wrapper.orderBy("job.sort", true);
        List<Job> jobs = this.baseMapper.getJobByProductId(wrapper);
        if(jobs != null && jobs.size()>0){
            return jobs;
        }
        return null;
    }

    @Override
    public Boolean updateJobByID(Job job) {
        return this.updateById(job);
    }
}
