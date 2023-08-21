package com.nellshark.repositories;

import com.nellshark.models.Company;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository extends AbstractGenericRepository<Company> {

  public CompanyRepository() {
    super(List.of(new Company(1L)));
  }
}
