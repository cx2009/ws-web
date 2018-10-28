package com.jsxk.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private int id;

    private String orderId;

    private int orderState;

    private double amount;


    private int payWay;

    private String payFrom;


    private String payTo;

    private Date createTime;


}
