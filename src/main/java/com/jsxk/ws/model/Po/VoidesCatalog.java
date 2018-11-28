package com.jsxk.ws.model.Po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoidesCatalog {


    private int id;

    private String name;

    private int catalogid;

    private double size;

    private String type;

    private Date createtime;

    private String url;

    private String catalogName;

    private String remark;

    private String label;

    private Boolean isStore;

    private String isDispaly;

    private int numlook;

    private String img;

    private  int parentId;

    private  String parentName;


}
