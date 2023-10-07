package com.nellshark.repositories;

import com.nellshark.models.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CustomGenericRepository<Employee, Long> {

}
