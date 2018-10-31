package com.jsxk.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Novel {


    private int id;


    private String summary;


    private Date createtime;


    private String title;


    private String content;


    private int catalogId;

}
