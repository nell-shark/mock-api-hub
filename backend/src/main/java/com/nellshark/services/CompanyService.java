package com.nellshark.services;

import com.nellshark.exceptions.CompanyNotFoundException;
import com.nellshark.models.Company;
import com.nellshark.repositories.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public Company getCompanyById(Long id) {
    return companyRepository
        .findById(id)
        .orElseThrow(
            () -> new CompanyNotFoundException("Company with id %s not found".formatted(id)));
  }
}
