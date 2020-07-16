package com.cops.nc65.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cops.entity.nc65.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao extends BaseMapper<Employee> {
    //查询。@Param对应参数属性注解，There is no getter for property named 'xx' in 'class java.lang.Integer
    List<Employee> testQuery(@Param("EMPNO")Integer EMPNO);
}
