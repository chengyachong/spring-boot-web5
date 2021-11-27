package com.cyc.bootcase.controller;

import com.cyc.bootcase.bean.Employee;
import com.cyc.bootcase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
 @Autowired
    EmployeeService employeeService;
 @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
       return employeeService.getEmployee(id);
    }

  @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee employee1 =  employeeService.updateEmp(employee);
  return employee1;
     }
@GetMapping("/emp/lastName/{lastName}")
     public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
     return  employeeService.getEmpByLastName(lastName);
     }
}
