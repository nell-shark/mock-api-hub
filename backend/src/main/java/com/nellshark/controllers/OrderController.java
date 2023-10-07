package com.nellshark.controllers;

import com.nellshark.models.Item;
import com.nellshark.models.Order;
import com.nellshark.services.OrderService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController extends AbstractGenericController<Order, Long> {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    super(orderService);
    this.orderService = orderService;
  }

  @GetMapping("/{id}/items")
  public ResponseEntity<List<Item>> getItemsByOrderId(@PathVariable("id") Long orderId) {
    List<Item> items = orderService.getItemsByOrderId(orderId);
    return ResponseEntity.ok(items);
  }
}
