package com.jsxk.ws.dao;


import com.jsxk.ws.model.Initialization;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InitializationDao {


    @Select("select * from initialization  where type=#{type} order by createtime limit 1")
    Initialization getInitialization(int type);

    @Insert("insert into initialization( domain,version,versionurl,necessary,type,remark) vaule （#{domain},#{version},#{versionurl},#{necessary},#{type},#{remark}）")
    int addnitialization(Initialization initialization);



    @Select("select * from initialization  order by createtime")
    List <Initialization> getLastInitialization();


}
