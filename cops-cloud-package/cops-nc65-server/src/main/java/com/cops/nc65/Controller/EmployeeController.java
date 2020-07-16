package com.cops.nc65.Controller;


import com.cops.library.until.NC65RestResponse;
import com.cops.entity.nc65.Employee;
import com.cops.nc65.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("list")
    @ResponseBody
    public NC65RestResponse list(Integer EMPNO){
        List<Employee> saleOrders = employeeService.testQuery(EMPNO);
        return NC65RestResponse.success().setData(saleOrders);
    }
}