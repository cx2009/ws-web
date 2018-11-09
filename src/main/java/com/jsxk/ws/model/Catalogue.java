package com.jsxk.ws.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue {


    private int id;

    @NotEmpty(message = "name 不能为空！")
    private String name;

    @NotEmpty(message = "parentId 不能为空！")
    private int parentId;

    private Date createtime;

    private int indexs;

    @NotEmpty(message = "cover 不能为空！")
    private String cover;

    @NotEmpty(message = "描述信息不能为空！")
    private String described;
}
