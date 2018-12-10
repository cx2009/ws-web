package com.jsxk.ws.controller.App;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jsxk.ws.common.AuthManager;
import com.jsxk.ws.common.Reults;
import com.jsxk.ws.common.ShareCodeUtil;
import com.jsxk.ws.common.UUIDUtils;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.model.Bo.LogIn;
import com.jsxk.ws.model.Bo.UserRegit;
import com.jsxk.ws.model.Initialization;
import com.jsxk.ws.model.Po.UserTitie;
import com.jsxk.ws.model.UserInfor;
import com.jsxk.ws.service.UserServcie;
import com.jsxk.ws.utils.ControllerUtils;
import com.jsxk.ws.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    UserServcie userServcie;

    @Autowired
    AuthManager authManager;


    @RequestMapping(value = "/regit", method = RequestMethod.POST)
    public String regint(@RequestBody @Valid UserRegit userRegit, BindingResult bindingResult) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        if (bindingResult.hasErrors()) {
            resultJson.put("message", bindingResult.getFieldError().getDefaultMessage());
            resultJson.put("state", false);
            return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);
        }

        try {


            Reults reults = userServcie.checkuserId(userRegit.getEmail());
            if (reults == null || !reults.isSuccess()) {
                resultJson.put("message", reults.getMessage());
                resultJson.put("state", reults.isSuccess());
                return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);
            }

            UserInfor userInfor = new UserInfor();
            userInfor.setPassworld(userRegit.getPassword());
            userInfor.setName(userRegit.getName());
            userInfor.setState(1);
            userInfor.setType(1);
            userInfor.setEmail(userRegit.getEmail());
            userInfor.setUserId(UUIDUtils.getUUID());
            userInfor.setSex(0);
            userInfor.setRegitCode(ShareCodeUtil.generateShortUuid());
            reults = userServcie.regitUser(userInfor);

            String token = authManager.getToken(userInfor);
            userServcie.updateTokentime(userInfor.getEmail(), token);
            resultJson.put("token", token);
            resultJson.put("message", reults.getMessage());
            resultJson.put("state", reults.isSuccess());


        } catch (Exception ex) {

            log.error("注册错误{}", ex);

            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);


        }
        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/regitCode")
    public String regitsucces(@RequestParam("code") String code) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "登陆失败！");

        resultJson.put("state", false);

        try {

            UserInfor userInfor = userServcie.getMailCodeByUserId(code);

            if (userInfor != null) {

                Reults reults = userServcie.changeState(userInfor.getId(), true);

                resultJson.put("message", reults.getMessage());

                resultJson.put("state", reults.isSuccess());

            }

        } catch (Exception ex) {

            log.error("激活失败{}", ex);


            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody @Valid LogIn logIn, BindingResult bindingResult) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        resultJson.put("message", "登陆失败！");
        resultJson.put("state", false);
        resultJson.put("token", "");


        if (bindingResult.hasErrors()) {
            resultJson.put("message", bindingResult.getFieldError().getDefaultMessage());
            resultJson.put("state", false);
            return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);
        }

        try {

            Reults reults = userServcie.checkUserPwd(logIn.getPassword(), logIn.getLoadinId());

            if (reults.isSuccess()) {
                UserInfor userInfor = userServcie.getUerInforByMail(logIn.getLoadinId());
                String token = authManager.getToken(userInfor);
                userServcie.updateTokentime(logIn.getLoadinId(), token);
                userInfor.setPassworld("");
                resultJson.put("token", token);
                resultJson.putPOJO("data", userInfor);
            }

            resultJson.put("message", reults.getMessage());

            resultJson.put("state", reults.isSuccess());


        } catch (Exception ex) {

            log.error("登陆错误{}", ex);

            return ControllerUtils.renderControllerResult(ErrorCodes.systemError(), resultJson);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);

    }

    /***
     *
     * 获取用户信息
     * @return
     */

    @RequestMapping(value = "/getuserInfo", method = RequestMethod.GET)
    public String getUser(@RequestHeader("token") String token) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        UserTitie userTitie = userServcie.getUserInforByTOken(token);

        resultJson.putPOJO("usertitle", userTitie);

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


    @RequestMapping("/getInitialization")
    public String getInitialization(@RequestParam("type") int type) {

        ObjectNode resultJson = OBJECT_MAPPER.createObjectNode();

        try {

            Initialization initialization = userServcie.getInitialization(type);
            if (initialization == null)

            {
                initialization = new Initialization();
            }

            resultJson.putPOJO("data", initialization);

        } catch (Exception ex) {

            log.error("初始化信息错误{}", ex);

        }

        return ControllerUtils.renderControllerResult(ErrorCodes.success(), resultJson);


    }


}
