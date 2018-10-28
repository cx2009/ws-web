package com.jsxk.ws.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue {


    private int id;

    private String name;

    private int parentId;

    private Date createtime;

    private int indexs;

    private String cover;

    private String desc;
}
