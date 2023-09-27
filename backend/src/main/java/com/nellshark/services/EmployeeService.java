package com.nellshark.services;

import com.nellshark.exceptions.EmployeeNotFoundException;
import com.nellshark.models.Employee;
import com.nellshark.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public Employee getEmployeeById(Long id) {
    return employeeRepository
        .findById(id)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Employee with id %s not found".formatted(id)));
  }

}
