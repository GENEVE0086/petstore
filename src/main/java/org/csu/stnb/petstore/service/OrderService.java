package org.csu.stnb.petstore.service;

import java.util.List;

import org.csu.stnb.petstore.domain.Order;

public interface OrderService {
  void insertOrder(Order order);

  Order getOrder(int orderId);

  List<Order> getOrdersByUsername(String username);

  int getNextId(String name);
}
