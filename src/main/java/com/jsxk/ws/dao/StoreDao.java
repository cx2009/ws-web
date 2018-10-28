package com.jsxk.ws.dao;

import com.jsxk.ws.model.Store;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreDao {


    @Insert("insert into store (voidesId,userid) values(#{voidesId},#{userId})")
    int addStore(int voidesId, int userId);

    @Select("select * from store where userid=#{userId}")
    List<Store> getStoreListByuserId(int userId);

    @Delete("delete form store where userid=#{userId} and voideId=#{voideId}")
    int deletStore(int voideId, int userId);

    @Select("select count(1) from store where voidesId=? and  userid=?")
    int getStore(int voidesId, int userId);

}
