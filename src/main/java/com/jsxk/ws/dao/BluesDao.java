package com.jsxk.ws.dao;

import com.jsxk.ws.model.Blues;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BluesDao {


    @Select("select * from blues order By indexs")
    List<Blues> getBluesList();


}
