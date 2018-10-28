package com.jsxk.ws.model.Po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppMangers {

    private int id;

    private String name;

    private String remark;

    private String url;

    private String imagesurl;

    private Date createtime;

    private int catalogueId;

    private String catalogueName;


}
