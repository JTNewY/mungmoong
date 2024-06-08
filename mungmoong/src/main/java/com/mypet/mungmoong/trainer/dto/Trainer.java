package com.mypet.mungmoong.trainer.dto;

import java.util.ArrayList;
import java.util.Collections;
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

    private Users user;
    
    private List<String> careerNames;
    private List<Integer> careerNos; // 추가된 필드
    private List<String> certificateNames;

    private MultipartFile thumbnail;
    private List<MultipartFile> files;          // 업로드시 파일 객체

    private int fileNo;
    private Files imgFile; // 이미지 파일 조회 시 씀

    private List<Career> careerList = new ArrayList<>(); // 초기화
    private List<Certificate> certificateList = new ArrayList<>(); // 초기화
    private List<Schedule> scheduleList = new ArrayList<>();



    // 트레이너에게 리스트로 담기 위해 사용.
    // 조회할 때가 아니라 데이터를 insert할 때 사용!
    public List<Career> toCareerList() {
        List<Career> careers = new ArrayList<>();
    
        for (int i = 0; i < careerNames.size(); i++) {
            Career career = new Career();
            career.setName(careerNames.get(i));
            career.setUserId(this.userId);
            career.setTrainerNo(this.no);
            
    
            // careerNos가 있고 현재 인덱스가 careerNos 크기 내에 있는 경우
            if (careerNos != null && i < careerNos.size() && careerNos.get(i) != null) {
                career.setNo(careerNos.get(i)); // Career 클래스에 setNo 메소드가 있다고 가정
            }
    
            careers.add(career);
        }
    
        return careers;
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
