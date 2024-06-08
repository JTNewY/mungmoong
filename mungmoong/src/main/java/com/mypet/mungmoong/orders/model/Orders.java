package com.mypet.mungmoong.orders.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Orders {
    private String ID;
    private String productId;
    private String userId;
    private int petNo;
    private int trainerNo;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date resDate;
    private String memo;
    private String title;
    private int totalPrice;
    private String status;
    private Date regDate;
    private Date updDate;
}
