package com.nellshark.repositories;

import com.nellshark.models.Employee;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends AbstractGenericRepository<Employee> {

  public EmployeeRepository() {
    super(List.of(new Employee(1L)));
  }
}
