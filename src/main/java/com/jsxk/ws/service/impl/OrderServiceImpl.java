package com.jsxk.ws.service.impl;

import com.jsxk.ws.dao.OrdersDao;
import com.jsxk.ws.model.Bo.OrderQuery;
import com.jsxk.ws.model.Order;
import com.jsxk.ws.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrdersDao ordersDao;

    @Override
    public List<Order> getOrderListByQuery(OrderQuery orderQuery) {


        return ordersDao.getOrderListByOrderQuery(orderQuery);
    }

    @Override
    public Boolean createOrder(Order order) {
        return ordersDao.createOrder(order) > 0;
    }

    @Override
    public Boolean updateOrder(int orderId, int state) {
        return ordersDao.updateOrder(orderId, state) > 0;
    }

    @Override
    public String CreateOrederId(String userID) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result + userID;

    }
}
