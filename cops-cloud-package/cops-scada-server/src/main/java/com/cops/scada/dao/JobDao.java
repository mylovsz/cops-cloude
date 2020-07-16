package com.cops.scada.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cops.scada.entity.Job;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.scada.entity.VO.JobVO;
import com.cops.scada.entity.VO.PlanDayVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 作业（工艺）管理 Mapper 接口
 * </p>
 *
 * @author wanglm
 * @since 2019-07-24
 */
public interface JobDao extends BaseMapper<Job> {
    List<JobVO> getJobVOByPlanIdAJobSN(@Param("ew") EntityWrapper<JobVO> wrapper);

    List<JobVO> getPageJobVO(Page<JobVO> page, @Param("ew") EntityWrapper<JobVO> wrapper);

    List<Job> getJobByProductId(@Param("ew") EntityWrapper<Job> wrapper);
}
