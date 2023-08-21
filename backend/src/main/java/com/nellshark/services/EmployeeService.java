package com.nellshark.services;

import com.nellshark.models.Employee;
import com.nellshark.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends AbstractGenericService<Employee> {

  public EmployeeService(EmployeeRepository employeeRepository) {
    super(employeeRepository);
  }
}
