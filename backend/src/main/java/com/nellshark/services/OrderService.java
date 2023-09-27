package com.nellshark.services;

import com.nellshark.exceptions.OrderNotFoundException;
import com.nellshark.models.Order;
import com.nellshark.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order getOrderById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new OrderNotFoundException("Order with id %s not found".formatted(id)));
  }
}
