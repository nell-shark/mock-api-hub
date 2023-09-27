package com.nellshark.controllers;

import com.nellshark.models.Employee;
import com.nellshark.services.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController extends AbstractGenericController<Employee, Long> {

  public EmployeeController(EmployeeService employeeService) {
    super(employeeService);
  }
}
