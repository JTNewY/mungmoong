package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.orders.dto.OrdersDetail;

@Mapper
public interface OrdersDetailMapper {
       // 결제 상세 목록
       public List<OrdersDetail> list() throws Exception;
       // 결제 상세 조회
       public OrdersDetail select(int order_id) throws Exception;
       // 결제 상세 등록
       public Integer insert(OrdersDetail ordersDetail) throws Exception;
       // 결제 상세 수정
       public Integer update(OrdersDetail ordersDetail) throws Exception;
       // 결제 상세 삭제
       public Integer delete(int order_id) throws Exception;
       
       @Override
       String toString();
}
