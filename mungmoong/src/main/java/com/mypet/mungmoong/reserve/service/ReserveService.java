package com.mypet.mungmoong.reserve.service;

import java.util.List;

import com.mypet.mungmoong.reserve.dto.Reserve;

public interface ReserveService {
       // 예약 목록
       public List<Reserve> list() throws Exception;
       // 예약 조회
       public Reserve select(int date_no) throws Exception;
       // 예약 등록
       public Integer insert(Reserve reserve) throws Exception;
       // 예약 수정
       public Integer update(Reserve reserve) throws Exception;
       // 예약 삭제
       public Integer delete(int date_no) throws Exception;

       // 관리자 예약금액 조회 리스트
       public List<Reserve> listByUser() throws Exception;
       
       @Override
       String toString();
    
} 