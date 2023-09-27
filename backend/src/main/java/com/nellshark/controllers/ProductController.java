package com.nellshark.controllers;

import com.nellshark.models.Product;
import com.nellshark.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends AbstractGenericController<Product, Long> {

  public ProductController(ProductService productService) {
    super(productService);
  }
}
