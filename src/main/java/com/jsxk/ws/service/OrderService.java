package com.jsxk.ws.service;

import com.jsxk.ws.model.Bo.OrderQuery;
import com.jsxk.ws.model.Order;

import java.util.List;

public interface OrderService {


    List<Order> getOrderListByQuery(OrderQuery orderQuery);

    Boolean createOrder(Order order);

    Boolean updateOrder(int orderId, int state);


    String CreateOrederId(String userId);

}
