package com.jsxk.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distributor {



    private  String pid;

    private  String  code;

    private  String  channel;

    private Date createtime;

    private  int state;

    private  int id;

}
