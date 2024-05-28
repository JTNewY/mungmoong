package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.orders.dto.Orders;

@Mapper
public interface OrdersMapper {
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
