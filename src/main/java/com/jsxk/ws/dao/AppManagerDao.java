package com.jsxk.ws.dao;

import com.jsxk.ws.model.AppManager;
import com.jsxk.ws.model.Po.AppMangers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppManagerDao {


    @Insert("insert into app_manager (name ,remark,url ,imagesurl,catalogueId) values (#{name},#{remark},#{url},#{imagesurl},#{catalogueId})")
    int addAppManager(AppManager appManager);

    @Delete("delete from app_manager where id = #{id}")
    int deletAppManager(int id);

    @Update("update app_manager set name=#{name} ,remark=#{remark},url=#{url}, imagesurl=#{imagesurl}, catalogueId=#{catalogueId}  where id =#{id}")
    int editAppManager(AppManager appManager);

    @Select("select t.name as name,t.remark as remark ,t.url as url ,t.imagesurl as imagesurl,  t.createtime as createtime ,t.catalogueId as catalogueId , c.name as catalogueName  from app_manager t  inner join catalogue  c on t.catalogueId=c.id")
    List<AppMangers> getAppManagerList();


}
