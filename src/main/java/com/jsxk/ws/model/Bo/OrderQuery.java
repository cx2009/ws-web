package com.jsxk.ws.model.Bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderQuery {


    private String orderId;

    private String orderState;

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {


        this.startime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(startime) );




    }

    private String  startime;

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {


        this.endtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(endtime) );



    }

    private String endtime;


    private int pageNum;

    private int limit;

}
