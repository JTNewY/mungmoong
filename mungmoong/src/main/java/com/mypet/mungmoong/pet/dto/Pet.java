package com.mypet.mungmoong.pet.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Pet {
    private int petNo;
    private String petname;
    private String pettype;
    private int age;
    private int gender;
    private String character;
    private Date regDate;
    private Date updDate;
    private int orderNo;
    private String userId;
}