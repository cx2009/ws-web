package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayQuery {


    @NotEmpty(message = "金额不能为空！")
    private double amount;

    @NotEmpty(message = "sid 不能为空！")
    private String sid;

    @NotEmpty(message = "desc 不能为空！")
    private String nid;
}
