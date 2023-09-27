package com.nellshark.services;

import com.nellshark.models.Company;
import com.nellshark.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends AbstractGenericService<Company, Long> {

  public CompanyService(CompanyRepository companyRepository) {
    super(companyRepository);
  }
}
