package com.cops.nc65.service;

import com.baomidou.mybatisplus.service.IService;
import com.cops.entity.nc65.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    List<Employee> testQuery(Integer EMPNO);
}
