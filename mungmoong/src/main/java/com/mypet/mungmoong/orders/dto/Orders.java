package com.mypet.mungmoong.orders.dto;

import java.util.Date;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date resDate;

    private Boolean payCheck;
    private Boolean userCheck;
    private Integer trainerCheck;
}
