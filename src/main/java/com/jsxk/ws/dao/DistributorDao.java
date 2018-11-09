package com.jsxk.ws.dao;

import com.jsxk.ws.model.Distributor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DistributorDao {


    @Insert("insert into Distributor (pid,code,channel,state)value(?,?,?,?)")
    int addDistributor(Distributor distributor);


    @Select("select * from  Distributor  order by createtime")
    List<Distributor>getDistributor();



}
