package com.nellshark.services;

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
}
