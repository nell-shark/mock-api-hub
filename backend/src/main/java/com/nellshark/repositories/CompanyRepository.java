package com.nellshark.repositories;

import com.nellshark.models.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CustomGenericRepository<Company, Long> {

}
