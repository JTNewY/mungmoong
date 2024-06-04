package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.orders.model.Payments;


@Mapper
public interface PaymentsMapper {

    public List<Payments> list();

    public Payments select(String id);
    
    public int insert(Payments payments);
    
    public int update(Payments payments);
    
    public int delete(String id);

    // --------------------------------------
    public Payments selectByOrdersId(String ordersId);
}
