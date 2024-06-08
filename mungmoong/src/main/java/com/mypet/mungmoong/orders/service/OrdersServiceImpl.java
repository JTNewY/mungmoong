package com.mypet.mungmoong.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.orders.mapper.OrdersMapper;
import com.mypet.mungmoong.orders.model.Orders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public List<Orders> list() throws Exception {
        List<Orders> orderList = ordersMapper.list();
        return orderList;
    }

    @Override
    public Orders select(String id) throws Exception {
        Orders order = ordersMapper.select(id);
        return order;
    }

    @Override
    public int insert(Orders orders) throws Exception {
        int orderNo = ordersMapper.insert(orders);
        log.info("orderNo : " + orderNo);

        return orderNo;
    }

    @Override
    public int update(Orders orders) throws Exception {
        int result = ordersMapper.update(orders);
        return result;
    }

    @Override
    public int delete(String id) throws Exception {
        int result = ordersMapper.delete(id);
        return result;
    }

    @Override
    public List<Orders> listByUserId(String userId) throws Exception {
        List<Orders> orderList = ordersMapper.listByUserId(userId);
        return orderList;
    }

    

    
}
