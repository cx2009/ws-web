package com.jsxk.ws.model.Po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRecords {


    private int id;
    private String userId;


    private String name;

    private int type;

    private int state;

    private Date createtime;

    private double amount;

    private int record;


}
