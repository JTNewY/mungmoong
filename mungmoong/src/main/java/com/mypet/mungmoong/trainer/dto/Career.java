package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Career {
    private int no;                // 경력 번호
    private String userId;         // 회원 아이디
    private String name;           // 경력 명
    private Date regDate;          // 등록일
    private Date updDate;          // 수정일
    private int trainerNo;         // Trainer 번호

    private List<Files> files;     // 파일 리스트 (자격증 사진 등)
} 