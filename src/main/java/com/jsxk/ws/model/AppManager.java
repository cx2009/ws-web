package com.jsxk.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppManager {

    private int id;

    private String name;

    private String remark;

    private String url;

    private String imagesurl;

    private Date createtime;

    private int catalogueId;

}
