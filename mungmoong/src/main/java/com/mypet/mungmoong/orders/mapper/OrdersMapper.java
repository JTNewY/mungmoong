package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.orders.model.Orders;


@Mapper
public interface OrdersMapper {

    public List<Orders> list() throws Exception;
    
    public Orders select(String id) throws Exception;
    
    public int insert(Orders ordere) throws Exception;
    
    public int update(Orders ordere) throws Exception;
    
    public int delete(String id) throws Exception;

    public int selectOrderById() throws Exception;

    // ---------------------------------------------------------
    public List<Orders> listByUserId(String userId) throws Exception;
}
