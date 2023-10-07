package com.nellshark.services;

import com.nellshark.models.Dimension;
import com.nellshark.models.Product;
import com.nellshark.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractGenericService<Product, Long> {

  private static final Logger logger = LoggerFactory.getLogger(ProductService.class);


  public ProductService(ProductRepository productRepository) {
    super(productRepository);
  }

  public Dimension getDimensionsByProductId(Long productId) {
    logger.info("Getting dimensions by product id: {}", productId);
    return getEntityById(productId).getDimensions();
  }
}
