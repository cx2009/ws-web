package com.jsxk.ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Initialization {

    private int id;

    private String domain;

    private String version;

    private String versionurl;

    private boolean necessary;

    private int type;

    private  String remark;


}
