package com.nellshark.services;

import com.nellshark.models.Order;
import com.nellshark.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractGenericService<Order, Long> {

  public OrderService(OrderRepository orderRepository) {
    super(orderRepository);
  }
}
