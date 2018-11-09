package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {


    private String name;

    private int type;


    private int state;

    public void setStarTime(String starTime) {
        if(starTime==null||starTime=="")
        {

            this.starTime=null;
            return;

        }

        this.starTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(starTime) );

    }

    private String starTime;

    public void setEndTime(String endTime) {
        if(endTime==null||endTime=="")
        {

            this.endTime=null;
            return;

        }

        this.endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(endTime) );

    }

    private String endTime;

    private  int pageNum;



}
