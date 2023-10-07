package com.nellshark.controllers;

import com.nellshark.models.Dimension;
import com.nellshark.models.Product;
import com.nellshark.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends AbstractGenericController<Product, Long> {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    super(productService);
    this.productService = productService;
  }

  @GetMapping("/{id}/dimensions")
  public ResponseEntity<Dimension> getDimensionsByProductId(@PathVariable("id") Long productId) {
    Dimension dimensions = productService.getDimensionsByProductId(productId);
    return ResponseEntity.ok(dimensions);
  }
}
