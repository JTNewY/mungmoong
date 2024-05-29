package com.mypet.mungmoong.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.orders.dto.Orders;
import com.mypet.mungmoong.orders.mapper.OrdersMapper;


@Service
public class OrdersServiceImpl implements OrdersService {
    
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> list() throws Exception {
       List<Orders> ordersList = ordersMapper.list();
       return ordersList;  
     }

    @Override
    public Orders select(int order_no) throws Exception {
        Orders orders = ordersMapper.select(order_no);
        return orders; 
    }

    @Override
    public Integer insert(Orders orders) throws Exception {
        int result = ordersMapper.insert(orders);
        return result;
        }

    @Override
    public Integer update(Orders orders) throws Exception {
        int result = ordersMapper.update(orders);
        return result;
     }

    @Override
    public Integer delete(int order_no) throws Exception {
        int result = ordersMapper.delete(order_no);
        return result;
    }

}
