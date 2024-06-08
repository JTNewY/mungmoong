package com.mypet.mungmoong.orders.model;

import java.util.Date;

import lombok.Data;

@Data
public class Cancellations {
    private String id;
    private Orders order;
    private CancellationType type = CancellationType.CANCEL;
    private CancellationStatus status = CancellationStatus.PENDING;
    private String reason;
    private int refundedAmount = 0;
    private boolean isConfirmed = false;
    private boolean isRefund = false;
    private String accountNumber;
    private String bankName;
    private String depositor;
    private Date canceledAt;
    private Date completedAt;
    private Date createdAt;
    private Date updatedAt;
}
