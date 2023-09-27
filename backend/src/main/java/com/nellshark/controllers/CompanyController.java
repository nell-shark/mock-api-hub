package com.nellshark.controllers;

import com.nellshark.models.Company;
import com.nellshark.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

  private final CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
    Company company = companyService.getCompanyById(id);
    return ResponseEntity.ok(company);
  }
}
