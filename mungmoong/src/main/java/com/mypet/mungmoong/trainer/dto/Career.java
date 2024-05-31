package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Career {
    private int no;                // 경력 번호
    private String userId;         // 회원 아이디
    private String name;           // 경력 명
    private Date regDate;          // 등록일
    private Date updDate;          // 수정일


   // 파일 (한 건 이상)
   List<MultipartFile> file;  // 이건 자격증 사진용

   // 파일 번호
   // file_no -> fileNo (myBatis)
   private int fileNo;
}