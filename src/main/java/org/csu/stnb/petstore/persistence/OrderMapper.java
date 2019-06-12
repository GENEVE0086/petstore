package org.csu.stnb.petstore.persistence;

import org.csu.stnb.petstore.domain.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> getOrdersByUsername(String var1);

    Order getOrder(int var1);

    void insertOrder(Order var1);

    void insertOrderStatus(Order var1);
}
