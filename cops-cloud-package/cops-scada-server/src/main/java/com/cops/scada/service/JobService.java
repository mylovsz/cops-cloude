package com.cops.scada.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Job;
import com.baomidou.mybatisplus.service.IService;
import com.cops.scada.entity.VO.JobVO;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import java.util.List;

/**
 * <p>
 * 作业（工艺）管理 服务类
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
public interface JobService extends IService<Job> {

    /**
     * 获取模板数据
     * @return
     */
    List<Job> getJobTemplate();

    JobVO getJobVOBy(Long planId, String jobSn);

    JobVO getJobVOBy(String planSn, String jobSn);


    List<JobVO> getJobVOByPlanId(Long planId);

    JobVO getJobVOById(Long id);

    Boolean addJob(Job job);

    Page<JobVO> getPageJobVO(Page<JobVO> page, EntityWrapper<JobVO> wrapper);

    /**
     * 根据产品ID获取模板
     * @param productId
     * @return
     */
    List<Job> getJobTemplateByProductId(Long productId);

    Boolean updateJobByID(Job job);


}
