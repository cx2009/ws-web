package com.jsxk.ws.common.callback.HandlerInterceptor;

import com.jsxk.ws.common.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@Component
@WebFilter(urlPatterns = "/**", filterName = "monitorFilter")
public class TokenAuthorFilter implements Filter {

    private static final String[] excludePathPatterns = {"/user/regit", "/user/regitCode", "/user/login", "/user/getInitialization", "/account/login"};


    @Autowired
    AuthManager authManager;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;

        String url = req.getRequestURI();
        if (Arrays.asList(excludePathPatterns).contains(url)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse res = (HttpServletResponse) response;

        // res.sendError(403, "没有权限！");

        String accessToken = req.getHeader("token");
        if (null == accessToken) {

            res.sendError(403, "缺少token");
            return;
            // throw new CommonException(401, "无token，请重新登录");
        }

        /**
         if (authManager.TokenExpreTime(accessToken)) {

         res.sendError(403, "token 过期， 请从新登陆");
         return;
         }
         **/


        if (authManager.getUserInfoByToken(req) == null) {

            res.sendError(403, "请重新登陆");

            return;
            //需要重新登陆
        }

        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}
