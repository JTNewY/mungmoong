package com.mypet.mungmoong.users.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Users {
    private String id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
    private int enabled;
    private List<UserAuth> authList;
}
