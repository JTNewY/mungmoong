package com.mypet.mungmoong.orders.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Orders {
    private int orderNo;
    private int dateNo;
    private int orderId;
    private String userId;
    private Date regDate;
    private Boolean payCheck;
    private Boolean userCheck;
    private Integer trainerCheck;

    public int getDateNo() {
        return dateNo;
    }

    public void setDateNo(int dateNo) {
        this.dateNo = dateNo;
    }
}
