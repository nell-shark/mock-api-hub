package com.nellshark.services;

import com.nellshark.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }
}
