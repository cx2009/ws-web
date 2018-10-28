package com.jsxk.ws.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsxk.ws.common.AuthManager;
import com.jsxk.ws.common.Reults;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.Bo.AdLogin;
import com.jsxk.ws.model.Bo.LogIn;
import com.jsxk.ws.model.Bo.UserQuery;
import com.jsxk.ws.model.Po.UserRecords;
import com.jsxk.ws.model.Po.UserStore;
import com.jsxk.ws.model.UserInfor;
import com.jsxk.ws.service.UserServcie;
import com.jsxk.ws.utils.ControllerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Autowired
    private AuthManager authManager;


    @Autowired
    private UserServcie userServcie;

    @RequestMapping("/changpwd")
    public String changePwd(HttpServletRequest request, @RequestParam("newpwd") String newpwd, @RequestParam("oldpwd") String oldpwd) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            UserInfor userInfor = authManager.getUserInfoByToken(request);
            userInfor.setPassworld(oldpwd);
            Reults reults = userServcie.changePwd(newpwd, userInfor);

            resultJson.put("message", reults.getMessage());

            resultJson.put("state", reults.isSuccess());

        } catch (Exception ex) {

            log.error("变更错误", ex);

            Reults reults = new Reults();

            reults.setSuccess(false);

            reults.setMessage("系统异常！");
            resultJson.putPOJO("reults", reults);

            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public String getUserList(@RequestBody UserQuery userQuery) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Page page = PageHelper.startPage(userQuery.getPageNum(), 20);
            List<UserRecords> records = userServcie.getUserRecords(userQuery);

            resultJson.putPOJO("data", records);

            resultJson.put("pageNum", page.getPageNum());
            resultJson.put("pageSize", page.getPageSize());


        } catch (Exception ex) {


            log.error("查询用户错误{}", ex);
        }
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping(value = "/changeEnable", method = RequestMethod.POST)
    public String changeAccount(@RequestParam("state") boolean enbale, @RequestParam("id") int id) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Reults reults = userServcie.changeState(id, enbale);

            resultJson.put("message", reults.getMessage());

            resultJson.put("state", reults.isSuccess());

        } catch (Exception ex) {


            log.error("变更用户状态错误{}", ex);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody AdLogin logIn) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();
        try {

            if (!"admin".equals(logIn.getUsername())) {

                resultJson.put("message", "用户名错误！");

                resultJson.put("state", false);

                resultJson.putPOJO("data", "");

                resultJson.put("token", "");

            } else {

                Reults reults = userServcie.checkPwd(logIn.getPassword(), "3");

                if (reults.isSuccess()) {

                    UserInfor userInfor = userServcie.getUserInforByuserId("3");
                    String token = authManager.getToken(userInfor);

                    resultJson.put("message", "登陆成功！");

                    resultJson.put("state", true);

                    UserStore userStore = new UserStore();
                    userStore.setId(userInfor.getId());
                    userStore.setName(userInfor.getName());

                    resultJson.putPOJO("data", userStore);
                    resultJson.put("token", token);

                } else {


                    resultJson.put("message", "用户名或密码错误！");

                    resultJson.put("state", false);

                    resultJson.put("token", "");


                }
            }

        } catch (Exception ex) {
            log.error("登陆错误{}", ex);

        }
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }


}
