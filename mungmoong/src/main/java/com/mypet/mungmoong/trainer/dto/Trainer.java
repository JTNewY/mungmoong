package com.mypet.mungmoong.trainer.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    private List<Integer> careerNos; // 추가된 필드
    private List<String> certificateNames;

    private MultipartFile thumbnail;
    private List<MultipartFile> files;

    private int fileNo;
    private Files imgFile; // 이미지 파일 조회 시 씀

    private List<Career> careerList = new ArrayList<>(); // 초기화
    private List<Certificate> certificateList = new ArrayList<>(); // 초기화

    private Users user;

    public List<Career> toCareerList() {
        if (careerNames == null || careerNos == null) {
            return Collections.emptyList();
        }
        return IntStream.range(0, careerNames.size())
                        .mapToObj(index -> {
                            Career career = new Career();
                            career.setNo(careerNos.get(index));
                            career.setName(careerNames.get(index));
                            career.setUserId(this.userId);
                            career.setTrainerNo(this.no);
                            return career;
                        }).collect(Collectors.toList());
    }

    public List<Certificate> toCertificateList() {
        if (certificateNames == null) {
            return Collections.emptyList(); // 빈 리스트를 반환
        }
        return certificateNames.stream().map(name -> {
            Certificate certificate = new Certificate();
            certificate.setName(name);
            certificate.setUserId(this.userId);
            certificate.setTrainerNo(this.no);
            return certificate;
        }).collect(Collectors.toList());
    }
}
