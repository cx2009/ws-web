package com.jsxk.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfor {


    private int id;
    private String userId;

    private String passworld;

    private String name;

    private int type;

    private int state;

    private Date createtime;

    private String email;

    private int sex;

    private String regitCode;


}
