package com.mypet.mungmoong.users.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Users {
    private String userId;
    private String password;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String address;
    private String mail;
    private String phone;
    private Date regDate;
    private Date updDate;
    private Integer role;           // role : 0(유저),1(훈련사),2(관리자)
    private int enabled;
    private List<UserAuth> authList;
}
