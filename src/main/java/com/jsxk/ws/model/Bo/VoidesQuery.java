package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoidesQuery {


    private String name;

    private Date stattime;

    private Date endtime;

    private int pagenum;
}
