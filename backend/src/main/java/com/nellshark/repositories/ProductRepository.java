package com.nellshark.repositories;

import com.nellshark.models.Product;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public class ProductRepository extends AbstractGenericRepository<Product> {

  public ProductRepository() {
    super(List.of());
  }
}
