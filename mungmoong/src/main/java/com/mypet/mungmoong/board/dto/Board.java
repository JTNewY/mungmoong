package com.mypet.mungmoong.board.dto;

import lombok.Data;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Data
public class Board {
    private int no;
    private String title;
    private String content;
    private String fileName;
    private String userId;
    private Date regDate;
    private Date updDate;
    private Integer qnaType;
    private Boolean answered; 
    private String thumbnailPath; // 썸네일 경로
    private String createdAt; // 작성일자
    private MultipartFile thumbnail;
    private MultipartFile file;

}
