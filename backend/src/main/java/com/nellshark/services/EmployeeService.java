package com.nellshark.services;

import com.nellshark.models.Employee;
import com.nellshark.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public void saveEmployee(@NonNull Employee employee) {
    logger.info("Save employee: {}", employee);
    employeeRepository.save(employee);
  }
}
