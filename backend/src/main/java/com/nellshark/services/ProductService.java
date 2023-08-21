package com.nellshark.services;

import com.nellshark.models.Product;
import com.nellshark.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractGenericService<Product> {

  public ProductService(ProductRepository productRepository) {
    super(productRepository);
  }
}
