package com.mypet.mungmoong.orders.service;

import java.util.List;

import com.mypet.mungmoong.orders.dto.Orders;



public interface OrdersService {

    public List<Orders> list() throws Exception;

    // [은아] - 상태 수정
    public int updateMeaning(int no, int meaning) throws Exception;

    public List<Orders> listByTrainer(int trainerNo) throws Exception;

    public Orders select(int no) throws Exception;

    public int insert(Orders orders) throws Exception;

    public int update(Orders orders) throws Exception;

    public int delete(int no) throws Exception;

    // ---------------------------------------------------------
    public List<Orders> listByUserId(String userId) throws Exception;

    public int Status(Orders orders) throws Exception;
    
}
