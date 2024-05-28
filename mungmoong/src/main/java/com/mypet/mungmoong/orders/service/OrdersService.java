package com.mypet.mungmoong.orders.service;

import java.util.List;

import com.mypet.mungmoong.orders.dto.Orders;

public interface OrdersService {
     // 결제 목록
       public List<Orders> list() throws Exception;
       // 결제 조회
       public Orders select(int order_no) throws Exception;
       // 결제 등록
       public Integer insert(Orders orders) throws Exception;
       // 결제 수정
       public Integer update(Orders orders) throws Exception;
       // 결제 삭제
       public Integer delete(int order_no) throws Exception;
       
       @Override
       String toString();
}
