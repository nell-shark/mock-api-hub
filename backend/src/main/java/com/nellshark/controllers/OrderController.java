package com.nellshark.controllers;

import com.nellshark.models.Order;
import com.nellshark.services.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends AbstractGenericController<Order, Long> {

  public OrderController(OrderService orderService) {
    super(orderService);
  }
}
