package com.mypet.mungmoong.reserve.dto;

import java.util.Date;

import lombok.Data;
 
@Data
public class Reserve {
    private int no;         // 예약 번호
    private Date date;      // 예약 일자 (통합된 일자 - 후에 날짜, 시간 구분 가능성 ⭕)
    // private Date time;      // 예약 시간
    // private Date day;       // 예약 날짜
    private Date regDate;   // 등록 일자
    private Date updDate;   // 수정 일자
    private int petNo;      // 펫 번호
    private int trainerNo;  // 훈련사 번호
    private String userId;  // 회원 아이디
    private int orderNo;    // 주문 번호
    private String requst;  // 요청사항
}
