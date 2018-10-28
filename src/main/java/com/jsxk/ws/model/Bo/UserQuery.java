package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {


    private String name;

    private int type;


    private int state;

    private Date starTime;

    private Date endTime;

    private  int pageNum;


}
