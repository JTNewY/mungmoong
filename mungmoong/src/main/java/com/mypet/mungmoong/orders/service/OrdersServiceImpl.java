package com.mypet.mungmoong.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.orders.dto.Orders;
import com.mypet.mungmoong.orders.mapper.OrdersMapper;

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
    public List<Orders> listByTrainer(int trainerNo) throws Exception {
        List<Orders> orderList = ordersMapper.listByTrainer(trainerNo);
        return orderList;
    }
    
    @Override
    public Orders select(int no) throws Exception {
        Orders order = ordersMapper.select(no);
        return order;
    }

    @Override
    public int insert(Orders orders) throws Exception {
        int result = ordersMapper.insert(orders);
        return result;
    }

    @Override
    public int update(Orders orders) throws Exception {
        int result = ordersMapper.update(orders);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = ordersMapper.delete(no);
        return result;
    }

    @Override
    public List<Orders> listByUserId(String userId) throws Exception {
        List<Orders> orderList = ordersMapper.listByUserId(userId);
        return orderList;
    }

    // [은아]
    @Override
    public int updateMeaning(int no, int meaning) throws Exception {
        int result = ordersMapper.updateMeaning(no, meaning);
        return result;
    }

    // 상태 업데이트
    @Override
    public int Status(Orders orders) throws Exception {
        int result = ordersMapper.Status(orders);
        return result;
    }


    

    
}
