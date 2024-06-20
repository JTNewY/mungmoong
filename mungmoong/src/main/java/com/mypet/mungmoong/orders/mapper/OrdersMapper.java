package com.mypet.mungmoong.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mypet.mungmoong.orders.dto.Orders;


@Mapper
public interface OrdersMapper {

    public List<Orders> list() throws Exception;

    public List<Orders> listByTrainer(int trainerNo) throws Exception;

    // [은아] - 상태 수정
    public int updateMeaning(@Param("no") int no, @Param("meaning") int meaning) throws Exception;
    
    public Orders select(int no) throws Exception;
    
    public int insert(Orders order) throws Exception;
    
    public int update(Orders order) throws Exception;
    
    public int delete(int no) throws Exception;

    // ---------------------------------------------------------
    public List<Orders> listByUserId(String userId) throws Exception;

    public int Status(Orders orders) throws Exception;
}
