package com.nellshark.repositories;

import com.nellshark.models.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CustomGenericRepository<Order, Long> {

}
