package com.jsxk.ws.common;

import com.jsxk.ws.model.UserInfor;

public interface TokenManager {

    /**
     * 创建token
     *
     * @param userInfo
     * @return
     */
    String getToken(UserInfor userInfo);

    /**
     * 刷新用户
     *
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     *
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    UserInfor getUserInfoByToken(String token);
}
