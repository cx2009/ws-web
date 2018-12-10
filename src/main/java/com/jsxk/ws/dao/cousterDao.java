package com.jsxk.ws.dao;

import com.jsxk.ws.model.couster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface cousterDao {

    @Update("update coustem set url =#{url} where id =1")
    int editcouster(String url);

    @Select("select * from coustem limit 1")
    couster getcouster();
}
