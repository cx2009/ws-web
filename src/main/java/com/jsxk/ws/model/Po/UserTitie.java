package com.jsxk.ws.model.Po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTitie {


    private  String  userid;


    private  String email;

    private DateTime endtime;


    private  String name;

}
