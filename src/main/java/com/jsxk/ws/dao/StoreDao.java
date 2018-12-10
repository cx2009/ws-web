package com.jsxk.ws.dao;

import com.jsxk.ws.model.Po.VoidesContent;
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




    @Select("select t.id as id ,  t.`name` as `title`,t.label as label, t. volume as volume ,t.img as img ,t.url as url ,t.isdisplay as isDispaly ,t.url as url, IFNULL(s.id,0) as storeid from voides  t inner join store s on t.id=s.voidesId where s.userid =#{userId} order By t.catalogid ")
    List<VoidesContent> getVoidesByCatatlogue(String userId);



}
