package com.nellshark.repositories;

import com.nellshark.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CustomGenericRepository<Product, Long> {

}
