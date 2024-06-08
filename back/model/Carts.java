package com.mypet.mungmoong.orders.model;

import java.util.Date;

import com.mypet.mungmoong.users.dto.Users;

import lombok.Data;

@Data
public class Carts {
    private String id;
    private Products product;
    private Users user;
    private int amount = 1;
    private Date createdAt;
    private Date updatedAt;
}
