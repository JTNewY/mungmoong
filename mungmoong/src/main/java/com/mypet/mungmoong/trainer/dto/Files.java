package com.mypet.mungmoong.trainer.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Files {
    private int no;
    private String parentTable;    // 부모 테이블 정보 (Career, Certificate, Trainer)
    private int parentNo;          // 부모 엔티티 번호
    private String fileName;       // 파일 이름
    private String filePath;       // 파일 경로
    private long fileSize;         // 파일 크기
    private Date regDate;          // 등록일
    private Date updDate;          // 수정일
    private int fileCode;          // 파일 코드

    private MultipartFile file;    // MultipartFile 객체
}