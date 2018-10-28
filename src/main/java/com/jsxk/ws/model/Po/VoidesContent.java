package com.jsxk.ws.model.Po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoidesContent {

    private String img;

    private String title;

    //private String type;

    //private String type_tips;

    // 观看次数
    private String volume;

    private int id;

   // private String url;

    //是否收藏

    private Boolean isStore;

    //vip 是否可见
    private Boolean isDispaly;




}
