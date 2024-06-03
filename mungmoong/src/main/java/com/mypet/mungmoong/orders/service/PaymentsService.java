package com.mypet.mungmoong.orders.service;

import java.util.List;

import com.mypet.mungmoong.orders.model.Payments;


public interface PaymentsService {

    public List<Payments> list() throws Exception;

    public Payments select(String id) throws Exception;

    public int insert(Payments payments) throws Exception;
    
    public int update(Payments payments) throws Exception;
    
    public int delete(String id) throws Exception;
    
    // --------------------------------------
    public Payments selectByOrdersId(String ordersId);

    // merge : 없으면 insert, 있으면 update
    public int merge(Payments payments) throws Exception;
}