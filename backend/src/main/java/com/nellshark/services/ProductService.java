package com.nellshark.services;

import com.nellshark.exceptions.ProductNotFoundException;
import com.nellshark.models.Product;
import com.nellshark.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product getProductById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(
            () -> new ProductNotFoundException("Product with id %s not found".formatted(id)));
  }
}
