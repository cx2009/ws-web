package com.jsxk.ws.common.callback.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jsxk.ws.common.AuthManager;
import com.jsxk.ws.common.TokenManager;
import com.jsxk.ws.common.errorcode.ErrorCodes;
import com.jsxk.ws.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    AuthManager authManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {


        String accessToken = request.getHeader("token");
        if (null == accessToken)

        {

            response.sendError(403, "缺少token");

            return false;
            // throw new CommonException(401, "无token，请重新登录");
        }

        if (authManager.TokenExpreTime(accessToken)) {

            response.sendError(403,"token 过期， 请从新登陆");
            return false;
        }
        if (authManager.getUserInfoByToken(request) == null) {

            response.sendError(403, "请重新登陆");

            //需要重新登陆
        }


        return false;
    }


}
