package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * Board 
 * - 게시글 정보
 */
@Data
public class Board {
    private int no;
    private String user_id;        
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private Date updDate;
    
    // 썸네일 이미지 (한 건)
    MultipartFile thumbnail;
    
    // 파일 (한 건 이상)
    List<MultipartFile> file;

    // 파일 번호
    // file_no -> fileNo (myBatis)
    private int fileNo;
}
 