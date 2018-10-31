package com.jsxk.ws.dao;


import com.jsxk.ws.model.Initialization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InitializationDao {


    @Select("select * from initialization  where type=#{type}")
    Initialization getInitialization(int type);


}
