package com.nellshark.controllers;

import com.nellshark.models.Company;
import com.nellshark.services.CompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController extends AbstractGenericController<Company> {

  public CompanyController(CompanyService service) {
    super(service);
  }
}
