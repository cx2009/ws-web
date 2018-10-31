package com.jsxk.ws.service;

import com.jsxk.ws.common.Reults;
import com.jsxk.ws.model.Bo.UserQuery;
import com.jsxk.ws.model.Initialization;
import com.jsxk.ws.model.Po.UserRecords;
import com.jsxk.ws.model.UserInfor;
import org.joda.time.DateTime;

import java.util.List;

public interface UserServcie {


    Reults changePwd(String newPwd, UserInfor userInfor);


    List<UserRecords> getUserRecords(UserQuery userQuery);


    Reults changeState(int id, boolean enable);


    Reults regitUser(UserInfor userInfor);

    Reults checkPwd(String passworld, String userid);

    UserInfor getUserInforByuserId(String userid);

    UserInfor getMailCodeByUserId(String mailtoken);

    Reults checkuserId(String userId);

    Boolean updateTokentime(String userId, String token);

    Reults checkUserPwd(String passworld, String userId);

    UserInfor getUerInforByMail(String loginid);

    Initialization getInitialization(int type);


}
