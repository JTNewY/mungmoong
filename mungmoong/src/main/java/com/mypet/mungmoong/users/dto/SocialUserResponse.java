package com.mypet.mungmoong.users.dto;

import lombok.Data;

@Data
public class SocialUserResponse {
    private String userId;
    private String mail;
    private String name;

    public SocialUserResponse(String userId, String mail, String name) {
        this.userId = userId;
        this.mail = mail;
        this.name = name;
    }

    // Getters and Setters
}