package com.jsxk.ws.dao;

import com.jsxk.ws.model.Catalogue;
import com.jsxk.ws.model.Voides;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CatalogueDao {


    @Insert("insert into catalogue (name ,parentId,indexs,cover,described)values (#{name},#{parentId},#{indexs},#{cover},#{described})")
    int addCatalogue(Catalogue catalogue);

    @Delete("delete from catalogue where id =#{id}")
    int deleteCatalogue(@Param("id") int id);


    @Select("select * from catalogue where parentid = 0 order by indexs ")
    List<Catalogue> getCatalogueList();

    @Select("select * from catalogue where parentid =#{parentid} order by indexs")
    List<Catalogue> getCatalogueListByparentId(@Param("parentid") int parentid);

    @Update("update catalogue set name=#{name},indexs=#{indexs} where id =#{id}")
    int editCatalogue(Catalogue catalogue);

    @Select("select * from catalogue where parentid =#{parentId}")
    List<Catalogue> getCatalogueSecond(int parentId);


}
