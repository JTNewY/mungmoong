package com.mypet.mungmoong.reserve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.reserve.dto.Reserve;

@Mapper
public interface ReserveMapper {
       // 예약 목록
       public List<Reserve> list() throws Exception;
       // 예약 조회
       public Reserve select(int no) throws Exception;
       // 예약 등록
       public int insert(Reserve reserve) throws Exception;
       // 예약 수정
       public int update(Reserve reserve) throws Exception;
       // 예약 삭제
       public int delete(int date_no) throws Exception;

       // 관리자 예약금액 조회 리스트
       public List<Reserve> listByUser() throws Exception;
       
       @Override
       String toString();
}
