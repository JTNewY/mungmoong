package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.orders.model.OrderItems;


@Mapper
public interface OrderItemsMapper {

    public List<OrderItems> list() throws Exception;

    public OrderItems select(String id) throws Exception;
    
    public int insert(OrderItems orderItems) throws Exception;
    
    public int update(OrderItems orderItems) throws Exception;
    
    public int delete(String id) throws Exception;
    
    //------------------------------------------------------------------
    public List<OrderItems> listByOrderId(String ordersId) throws Exception;
}
