package com.example.AOPdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/add/employee")
    public Employee addEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee.getName(), employee.getEmpId());

    }

    @DeleteMapping(value = "/remove/employee/{id}")
    public String removeEmployee(@PathVariable String id) {

        employeeService.deleteEmployee(id);
        return "Employee removed";

    }

}
