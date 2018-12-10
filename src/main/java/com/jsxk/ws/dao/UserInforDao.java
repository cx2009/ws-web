package com.jsxk.ws.dao;


import com.jsxk.ws.model.Bo.UserQuery;
import com.jsxk.ws.model.Po.UserRecords;
import com.jsxk.ws.model.Po.UserTitie;
import com.jsxk.ws.model.UserInfor;
import org.apache.ibatis.annotations.*;
import org.joda.time.DateTime;

import java.util.List;


@Mapper
public interface UserInforDao {


    @Update("update  user_infor set passworld=#{newPwd} where id=#{id}")
    int changePwd(@Param("id") int id, @Param("newPwd") String newPwd);


    @Select("select count(1) from user_infor where id= #{id} and passworld= #{passworld}")
    int chenckPwd(UserInfor userInfor);


    @Select("select count(1) from user_infor where userid= #{userId} and passworld= #{passworld}")
    int chenckuserPwd(UserInfor userInfor);

    @Select({"<script>SELECT t.id,t.typetime as vipEndTime, t.userid,t.NAME,t.type,t.state,t.createtime,IFNULL(m.amount,0) as amount,IFNULL(m.record,0) as record\n" +
            "FROM user_infor t LEFT JOIN consumptio_record m ON t.userid = m.userid  where role=0 " +
            "<if test =\"name !=null and name !='' \">and t.name=#{name}</if>" +
            "<if test =\"type !=null and type != 3 \">and t.type=#{type}</if>" +
            "<if test =\"state !=null and state != 2 \">and t.state=#{state}</if>" +
            "<if test =\"starTime !=null and starTime!= '' \">and t.createtime &gt;= #{starTime}</if>" +
            "<if test =\"endTime !=null and endTime!= '' \">and t.createtime  &lt;= #{endTime}</if>" +
            " </script>"})
    List<UserRecords> getUserRecordByQuery(UserQuery userQuery);

    @Insert("insert into user_infor (name,type,state,passworld,userid,email,sex,regitCode)values( #{name},#{type},#{state},#{passworld},#{userId},#{email},#{sex},#{regitCode})")
    int addUser(UserInfor userInfor);

    @Select("select * from user_infor where userId=#{userid} LIMIT 1")
    UserInfor getUserinforByUserId(String userid);


    @Select("select * from user_infor where mailcode=#{mailtoken}")
    UserInfor getMailByCode(String mailtoken);

    @Update("update user_infor set state=#{state} where id =#{id}")
    int changeState(int id, int state);

    @Select("select count(1) from user_infor where email=#{eamil}")
    int checkuserId(String email);

    @Update("update user_infor set token=#{token} where email=#{userid}")
    int updateTokenByuserId(@Param("token") String token, @Param("userid") String userid);


    @Select("select count(1) from user_infor where email= #{email} and passworld= #{passworld}")
    int chenckuserPwdByEamil(UserInfor userInfor);


    @Select("select * from user_infor where email=#{userid} LIMIT 1")
    UserInfor getUserinforByLoginId(String userid);

    @Select("select userid, email ,typetime as endtime ,name  from user_infor where token=#{token}")
    UserTitie getUserTitleByToken(String token);

    @Update("update user_infor set typetime=#{endtime} where userid =#{userid}")
    int editUserInfo(@Param("userid") String userid, @Param("endtime") String endtime);


}
