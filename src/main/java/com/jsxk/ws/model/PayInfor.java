package com.jsxk.ws.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayInfor {


    private String mchNo;

    private String orderNo;

    private String transId;

    private int transAmt;

    private String notifyUrl;

    private String pageUrl;

    private String productName;

    private String sign;


}
