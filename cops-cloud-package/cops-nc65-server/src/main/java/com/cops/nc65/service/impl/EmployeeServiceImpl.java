package com.cops.nc65.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cops.nc65.dao.EmployeeDao;
import com.cops.entity.nc65.Employee;
import com.cops.nc65.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements EmployeeService {
    @Override
    public List<Employee> testQuery(Integer EMPNO) {
        return this.baseMapper.testQuery(EMPNO);
    }
}
