package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.users.dto.Users;

import lombok.Data;

@Data
public class Trainer {
    private int no;
    private String userId;
    private int orderNo;
    private String name;
    private String gender;
    private String birth;
    private String mail;
    private String phone;
    private String address;
    private String content;
    private Date regDate;
    private Date updDate;

    private List<String> careerNames;
    private List<String> certificateNames;

    private MultipartFile thumbnail;
    private List<MultipartFile> files;

    private int fileNo;

    private List<Career> careerList;  // 추가된 속성
    private List<Certificate> certificateList;  // 추가된 속성

    private Users user;

    public List<Career> toCareerList() {
        return careerNames.stream().map(name -> {
            Career career = new Career();
            career.setName(name);
            career.setUserId(this.userId);
            career.setTrainerNo(this.no);
            return career;
        }).collect(Collectors.toList());
    }

    public List<Certificate> toCertificateList() {
        return certificateNames.stream().map(name -> {
            Certificate certificate = new Certificate();
            certificate.setName(name);
            certificate.setUserId(this.userId);
            certificate.setTrainerNo(this.no);
            return certificate;
        }).collect(Collectors.toList());
    }
}
