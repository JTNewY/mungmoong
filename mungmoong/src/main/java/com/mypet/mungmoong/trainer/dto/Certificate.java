package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Certificate {
    private int no;                // 자격증 번호
    private String userId;         // 회원 아이디
    private String name;           // 자격증 명
    private Date regDate;          // 등록일
    private Date updDate;          // 수정일
    private int trainerNo;         // Trainer 번호

    private Files imgFile;          // 이미지 파일 조회 시 씀

    private MultipartFile thumbnail;        // 훈련사 프로필 이미지 업로드용
    private List<Files> files;      //
    private List<MultipartFile> multipartFilefiles;      //

    private MultipartFile InsertFile;

    public void insertImg() {
        Files fileInsert = new Files();
        fileInsert.setParentTable("certificate");
        // fileInsert.setParentNo(getNo());
        // fileInsert.setFileName(getName());
        // fileInsert.setFilePath("C:/upload/" + getName());
        fileInsert.setFileCode(0);
        fileInsert.setFile(InsertFile);
        setImgFile(fileInsert);
    }
}