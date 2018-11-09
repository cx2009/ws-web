package com.jsxk.ws.dao;

import com.jsxk.ws.model.Bo.VoidesQuery;
import com.jsxk.ws.model.Po.VoidesContent;
import com.jsxk.ws.model.Po.VoidesInfor;
import com.jsxk.ws.model.Voides;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface VoidesDao {


    @Insert("insert into voides (name,catalogid,size,type,url,remark,label,img) value (#{name},#{catalogid},#{size},#{type},#{url},#{remark},#{label},#{img})")
    int addVoides(Voides voides);

    @Update("update voides set name=#{name},catalogid=#{catalogid},size=#{size},type=#{type},url=#{url} where id =#{id}")
    int editVoides(Voides voides);

    @Delete("delete from voides where id =#{id}")
    int deletVoides(int id);

    @Select({"<script> select  t.id as id,t.regitCode as code, t.name as name ,c. NAME AS catalogName ,t.catalogid as catalogid ,  t.size as size , t.createtime as createtime , t.type as type from  voides t inner join  catalogue c  on t. catalogid= c.id  where t.role=0 " +
            "<if test =\"name !=null and name !='' \">and t.name like '%${name}%' </if> " +
            "<if test =\" stattime !=null and stattime !=''  \"> and t.createtime &lt;=  #{stattime} </if>"+
            "<if test =\" endtime !=null and endtime !='' \"> and t.createtime &gt;= #{endtime} </if>"+
            "<if test =\" catalogId !=null and catalogId !=0 \"> and t.catalogid = #{catalogId} </if> "+

            " </script>"})
    List<Voides> getVoidesList(VoidesQuery voidesQuery);


    @Select("select t.id,  t.`name` as `title`, t. volume as volume ,t.img as img ,t.isdisplay as isDispaly ,t.id as id ,t.url as url, IFNULL(s.id,0) as storeid from voides  t left join store s on t.id=s.voidesId where t.catalogid =#{CatalogueId} order By t.catalogid ")
    List<VoidesContent> getVoidesByCatatlogue(String CatalogueId, int state);

    @Select("select * from voides where id =#{id}")
    Voides getVoidesById(int id);


    @Select("select * from voides  where catalogid=#{catalogId} order by numlook ")
    List<Voides> getVoidesByCatalogId(int catalogId);

    @Update("update voides set numlook = numlook+1 where id=#{id}")
    int AddVoidesNum(int id);


}
