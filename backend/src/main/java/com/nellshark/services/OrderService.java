package com.nellshark.services;

import com.nellshark.models.Item;
import com.nellshark.models.Order;
import com.nellshark.repositories.OrderRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractGenericService<Order, Long> {

  private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

  public OrderService(OrderRepository orderRepository) {
    super(orderRepository);
  }

  public List<Item> getItemsByOrderId(Long orderId) {
    logger.info("Getting items by order id: {}", orderId);
    return getEntityById(orderId).getItems();
  }
}
