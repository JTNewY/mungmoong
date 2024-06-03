package com.mypet.mungmoong.orders.service;

import java.util.List;

import com.mypet.mungmoong.orders.model.Shipments;


public interface ShipmentsService {

    public List<Shipments> list() throws Exception;

    public Shipments select(String id) throws Exception;

    public int insert(Shipments shipments) throws Exception;

    public int update(Shipments shipments) throws Exception;

    public int delete(String id) throws Exception;

    public Shipments selectByOrdersId(String orderId) throws Exception;
}