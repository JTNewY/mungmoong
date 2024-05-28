package com.mypet.mungmoong.trainer.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Files {
    private int no;
    private String parentTable;
    private int parentNo;
    private String fileName;
    private String filePath;
    private long fileSize;
    private Date regDate;
    private Date updDate;
    private int fileCode;

    private MultipartFile file;
}
