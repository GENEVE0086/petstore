package org.csu.stnb.petstore.service;

import org.csu.stnb.petstore.domain.Order;

import java.util.List;

public interface OrderService {
    void insertOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getOrdersByUsername(String username);

    int getNextId(String name);
}
