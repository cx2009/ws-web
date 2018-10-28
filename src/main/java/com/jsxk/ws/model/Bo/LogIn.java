package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogIn {

    @NotEmpty(message = "登陆名不能为空！")
    @Email(message = "登陆名必须是邮箱！")
    private String loadinId;


    @NotEmpty(message = "密码不能为空！")
    private String password;
}
