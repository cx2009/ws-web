package com.jsxk.ws.dao;

import com.jsxk.ws.model.Store;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StoreDao {


    @Insert("insert into store (voidesId,userid) values(#{voidesId},#{userId})")
    int addStore(@Param("voidesId")String voidesId,@Param("userId") String userId);

    @Select("select * from store where userid=#{userId}")
    List<Store> getStoreListByuserId(@Param("userId") String userId);

    @Delete("DELETE from store where userid=#{userId} and voidesId=#{voideId}")
    int deletStore(@Param("voideId") String voideId, @Param("userId") String userId);

    @Select("select count(1) from store where voidesId=#{voidesId} and  userid=#{userId}")
    int getStore(@Param("voidesId") String voidesId,@Param("userId") String userId);

}
