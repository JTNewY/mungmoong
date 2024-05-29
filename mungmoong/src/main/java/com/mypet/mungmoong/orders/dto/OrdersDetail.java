package com.mypet.mungmoong.orders.dto;

import java.util.Date;



import lombok.Data;

@Data
public class OrdersDetail {
    private int orderId;
    private String cardNo;
    private String orderPrice;
    private Boolean priceCheck;
    private Date orderDate;
    private Boolean orderCheck;
    private int orderNo;
}
