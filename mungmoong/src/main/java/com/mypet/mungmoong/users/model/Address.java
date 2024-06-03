package com.mypet.mungmoong.users.model;

import java.util.Date;

import lombok.Data;

@Data
public class Address {
    private String id;
    private String userId;
    private String title; // 집, 회사, 사무실
    private String recipient;
    private String address;
    private String addressDetail;
    private String phone;
    private String request; // 문앞, 부재시문앞, 경비실, 택배함, 기타
    private Boolean isDefault = false; // 1: 기본배송지, 0: 기타
    private String accessNo; // 공동현관 출입번호
    private Date createdAt;
    private Date updatedAt;
}