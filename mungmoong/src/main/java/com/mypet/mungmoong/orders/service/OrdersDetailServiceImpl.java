package com.mypet.mungmoong.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.orders.dto.OrdersDetail;
import com.mypet.mungmoong.orders.mapper.OrdersDetailMapper;

@Service
public class OrdersDetailServiceImpl implements OrdersDetailService{
    @Autowired
    OrdersDetailMapper ordersDetailMapper;

    @Override
    public List<OrdersDetail> list() throws Exception {
       List<OrdersDetail> ordersDetailList = ordersDetailMapper.list();
       return ordersDetailList;  
      }

    @Override
    public OrdersDetail select(int order_id) throws Exception {
        OrdersDetail ordersDetail = ordersDetailMapper.select(order_id);
        return ordersDetail; 
      }

    @Override
    public Integer insert(OrdersDetail ordersDetail) throws Exception {

      
        int result = ordersDetailMapper.insert(ordersDetail);
        return result;
        }

    @Override
    public Integer update(OrdersDetail ordersDetail) throws Exception {
        int result = ordersDetailMapper.update(ordersDetail);
        return result;
      }

    @Override
    public Integer delete(int order_id) throws Exception {
        int result = ordersDetailMapper.delete(order_id);
        return result;
    }

}
