package com.jsxk.ws.model.Bo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegit {


    @Size(min = 5, max = 25, message = "邮箱不能为空！")
    @Email(message = "注册的必须是邮箱 ！")
    private String email;

    @NotEmpty(message = "密码不能为空！")
    @Size(min = 6, max = 25, message = "密码最短6个字符")
    private String password;

    @NotEmpty(message = "昵称不能为空！")
    @Size(min = 4, max = 25, message = "昵称长度4到25")
    private String name;

    private String code;

   // @NotEmpty(message = "性别不能为空！")
   // private int sex;

    // @NotEmpty(message = "登陆名不能为空！")

    // @Pattern(regexp = "[a-zA-Z0-9_-]{5,20}", message = "{登录名不能含有非法字符！}")
    // @Size(min = 5, max = 25, message = "昵称长度4到25")
    // private String loginid;

}
