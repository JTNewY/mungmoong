package com.mypet.mungmoong.orders.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class OrdersDetail {
    private UUID orderId;
    private String cardNo;
    private int dateNo;
    private String orderPrice;
    private Boolean priceCheck;
    private Date orderDate;
    private Boolean orderCheck;
    private int orderNo;
}
