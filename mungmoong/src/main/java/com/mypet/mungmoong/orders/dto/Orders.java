package com.mypet.mungmoong.orders.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.users.dto.Users;

import lombok.Data;

@Data
public class Orders {
    private int no;
    private String id;
    private String productId;
    private String userId;
    private int petNo;
    private int trainerNo;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date resDate;
    private String memo;
    private String title;
    private int price;
    private String status;
    private Date regDate;
    private Date updDate;
    private int meaning;

    // 주문자 정보
    private Users user;

    // 펫 정보
    private Pet pet;
}
