package com.mypet.mungmoong.trainer.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Trainer {
    private String trainerNo;      // 훈련사 번호
    private int orderNo;           // 결제 번호
    private String name;           // 이름
    private String gender;         // 성별
    private int age;               // 나이
    private String address;        // 주소
    private String career;         // 경력
    private String content;        // 소개
    private String certificate;    // 자격증
    private Timestamp regDate;     // 등록일
    private Timestamp updDate;     // 수정일
    private String userId;         // 회원 아이디
}