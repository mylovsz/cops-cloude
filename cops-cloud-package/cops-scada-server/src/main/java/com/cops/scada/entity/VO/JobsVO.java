package com.cops.scada.entity.VO;

import com.cops.scada.base.DataEntity;
import com.cops.scada.entity.Job;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.List;

public class JobsVO{
    @Getter
    @Setter
    private List<Job> jobs;
}
