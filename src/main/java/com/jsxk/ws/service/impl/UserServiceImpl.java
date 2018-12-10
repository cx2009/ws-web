package com.jsxk.ws.service.impl;

import com.jsxk.ws.common.Reults;
import com.jsxk.ws.dao.DistributorDao;
import com.jsxk.ws.dao.InitializationDao;
import com.jsxk.ws.dao.UserInforDao;
import com.jsxk.ws.model.Bo.UserQuery;
import com.jsxk.ws.model.Distributor;
import com.jsxk.ws.model.Initialization;
import com.jsxk.ws.model.Po.UserRecords;
import com.jsxk.ws.model.Po.UserTitie;
import com.jsxk.ws.model.UserInfor;
import com.jsxk.ws.service.UserServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserServcie {


    @Autowired
    private UserInforDao userInforDao;

    @Autowired
    private InitializationDao initializationDao;

    @Autowired
    private DistributorDao distributorDao;

    @Override
    public Reults changePwd(String newPwd, UserInfor userInfor) {

        Reults reults = new Reults();
        reults.setSuccess(false);

        try {

            userInfor.setPassworld(DigestUtils.md5DigestAsHex(userInfor.getPassworld().getBytes()));

            if (userInforDao.chenckPwd(userInfor) > 0) {

                newPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
                if (userInforDao.changePwd(userInfor.getId(), newPwd) > 0) {
                    reults.setSuccess(true);
                    reults.setMessage("变更成功！");
                    return reults;

                } else

                {
                    reults.setMessage("变更失败 ！");

                    return reults;
                }

            } else {

                reults.setSuccess(false);
                reults.setMessage("旧密码错误！");
                return reults;
            }

        } catch (Exception ex) {

            log.error("变更密码错误{}", ex);

        } finally {

            return reults;

        }

    }

    @Override
    public List<UserRecords> getUserRecords(UserQuery userQuery) {


        return userInforDao.getUserRecordByQuery(userQuery);

    }

    @Override
    public Reults changeState(int id, boolean enable) {

        Reults reults = new Reults();
        reults.setSuccess(false);
        reults.setMessage("变更失败");

        try {

            Boolean state = userInforDao.changeState(enable ? 1 : 0, id) > 0;
            if (state) {
                reults.setSuccess(true);
                reults.setMessage("变更成功");
            }
        } catch (Exception ex) {

            log.error("变更错误{}", ex);

        }
        return reults;
    }

    @Override
    public Reults regitUser(UserInfor userInfor) {

        Reults reults = new Reults();
        reults.setSuccess(false);
        reults.setMessage("注册失败！");
        try {

            userInfor.setPassworld(DigestUtils.md5DigestAsHex(userInfor.getPassworld().getBytes()));
            Boolean result = userInforDao.addUser(userInfor) > 0;

            if (result) {
                reults.setSuccess(true);
                reults.setMessage("注册成功！");
            }
        } catch (Exception ex) {

            log.error("注册错误{}", ex);
        }
        return reults;
    }

    @Override
    public Reults checkPwd(String passworld, String userid) {

        Reults reults = new Reults();

        reults.setSuccess(false);
        reults.setMessage("密码错误！");

        try {

            UserInfor userInfor = new UserInfor();
            userInfor.setPassworld(DigestUtils.md5DigestAsHex(passworld.getBytes()));
            userInfor.setUserId(userid);
            userInfor.setId(3);
            Boolean reult = userInforDao.chenckPwd(userInfor) > 0;

            if (reult) {
                reults.setSuccess(true);
                reults.setMessage("验证成功！");
            }

        } catch (Exception ex) {

            log.error("密码错误{}", ex);
        }

        return reults;

    }

    @Override
    public UserInfor getUserInforByuserId(String userid) {

        return userInforDao.getUserinforByUserId(userid);
    }

    @Override
    public UserInfor getMailCodeByUserId(String userId) {

        try {

            return userInforDao.getMailByCode(userId);


        } catch (Exception ex) {

            log.error("获取邮件code错误{}", ex);

        }
        return null;
    }

    @Override
    public Reults checkuserId(String userId) {

        Reults reults = new Reults();

        reults.setSuccess(true);
        reults.setMessage("");

        if (userInforDao.checkuserId(userId) > 0) {
            reults.setSuccess(false);
            reults.setMessage("用户名已经存在！");

        }
        return reults;
    }

    @Override
    public Boolean updateTokentime(String userId, String token) {

        return userInforDao.updateTokenByuserId(token, userId) > 0;

    }

    @Override
    public Reults checkUserPwd(String passworld, String email) {


        Reults reults = new Reults();
        reults.setSuccess(false);
        reults.setMessage("密码错误！");

        try {

            UserInfor userInfor = new UserInfor();
            userInfor.setPassworld(DigestUtils.md5DigestAsHex(passworld.getBytes()));
            userInfor.setEmail(email);
            Boolean reult = userInforDao.chenckuserPwdByEamil(userInfor) > 0;
            if (reult) {
                reults.setSuccess(true);
                reults.setMessage("验证成功！");
            }

        } catch (Exception ex) {

            log.error("密码错误{}", ex);
        }

        return reults;

    }

    @Override
    public UserInfor getUerInforByMail(String loginid) {
        return userInforDao.getUserinforByLoginId(loginid);
    }

    @Override
    public Initialization getInitialization(int type) {

        try {

        } catch (Exception ex)

        {

        }

        return initializationDao.getInitialization(type);
    }

    @Override
    public UserTitie getUserInforByTOken(String token) {
        return userInforDao.getUserTitleByToken(token);
    }

    @Override
    public boolean addInitialization(Initialization type) {


        return initializationDao.addnitialization(type) > 0;
    }

    @Override
    public List<Initialization> getInitialization() {
        return initializationDao.getLastInitialization();
    }

    @Override
    public Boolean addDistributor(Distributor distributor) {
        return distributorDao.addDistributor(distributor) > 0;
    }

    @Override
    public List<Distributor> getDistributor() {
        return distributorDao.getDistributor();
    }

    @Override
    public Boolean editUserinfro(String userid, String endtime) {
        return userInforDao.editUserInfo(userid, endtime) > 0;
    }


}
