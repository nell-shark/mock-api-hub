package com.nellshark.repositories;

import com.nellshark.models.Order;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends AbstractGenericRepository<Order> {

  public OrderRepository() {
    super(List.of());
  }
}
