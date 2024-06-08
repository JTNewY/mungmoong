package com.mypet.mungmoong.orders.model;

import java.util.Date;

import lombok.Data;

@Data
public class Payments {
    private String id;
    private String ordersId;
    private String paymentMethod;
    private PaymentsStatus status;
    private Date paidAt;
    private Date createdAt;
    private Date updatedAt;
}
