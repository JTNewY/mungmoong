package com.mypet.mungmoong.orders.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Orders {

    private UUID ID; // 추가된 필드
    private int orderNo;
    private int dateNo;
    private int orderId;
    private String userId;
    private String memo;
    private String title; // 추가된 필드
    private String addressId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date resDate;
    private int totalQuantity;
    private int totalCount;
    private int totalPrice;
    private Boolean payCheck;
    private Boolean userCheck;
    private Integer trainerCheck;

    private List<String> productId; // 추가
    private List<Integer> quantity; // 추가
}
