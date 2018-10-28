package com.jsxk.ws.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptioRecord {

    private int id;

    private int userId;

    private double amount;

    private int record;

}




