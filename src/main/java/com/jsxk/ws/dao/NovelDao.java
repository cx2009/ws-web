package com.jsxk.ws.dao;

import com.jsxk.ws.model.Novel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NovelDao {


    @Select("select title,summary,id,createtime from novel where catalogId=#{catalogId}")
    List<Novel> getNovelList(int catalogId);

    @Select("select * from novel  where id = #{id}")
    Novel getNovelById(int id);


}
