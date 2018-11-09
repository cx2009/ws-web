package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoidesQuery {


    private String name;


    public void setStattime(String stattime) {
        if(stattime==null|| stattime=="")
        {
            this.stattime="";
            return;
        }
        this.stattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(stattime) );


    }

    private String stattime;


    public void setEndtime(String endtime) {

        if(endtime==null||endtime=="")
        {

            this.endtime=null;
            return;

        }

        this.endtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(endtime) );

    }

    private String endtime;


    private  int catalogId;



    private int pagenum;


}
