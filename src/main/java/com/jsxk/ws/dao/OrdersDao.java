package com.jsxk.ws.dao;

import com.jsxk.ws.model.Bo.OrderQuery;
import com.jsxk.ws.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrdersDao {

    @Select({"<script> select * from  orders  where createtime  &gt;=  #{startime} and createtime &lt;=  #{endtime} " +
            "<if test =\"orderId !=null and orderId !='' \">and orderId=#{orderId}</if> " +
            "<if test= \"orderState !=null and orderState !=''  \"> and orderstate =#{orderState} </if>" +
            " </script>"})
    List<Order> getOrderListByOrderQuery(OrderQuery orderQuery);

    @Insert("insert into order (orderid,orderstate,amount,payway,payfrom,payto)values(#{orderId},#{orderState},#{amount},#{payWay},#{payFrom,},#{payTo})")
    int createOrder(Order order);


    @Update("update order set  orderstate=#{state} where orderid=#{orderId}")
    int updateOrder(int orderId, int state);

    @Select("select * from orders where id = #{id} ")
    Order getOrderById(int id);

}
