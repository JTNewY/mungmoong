package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Trainer {
    private String no;             // 훈련사 번호
    private int orderNo;           // 결제 번호
    private String name;           // 이름
    private String gender;         // 성별
    private String birth;          // 생일
    private String address;        // 주소
    private String career;         // 경력
    private String content;        // 소개
    private String certificate;    // 자격증
    private Date regDate;     // 등록일
    private Date updDate;     // 수정일
    private String userId;         // 회원 아이디

   // 썸네일 이미지 (한 건)
   MultipartFile thumbnail;

   // 파일 (한 건 이상)
   List<MultipartFile> file;

   // 파일 번호
   // file_no -> fileNo (myBatis)
   private int fileNo;
}