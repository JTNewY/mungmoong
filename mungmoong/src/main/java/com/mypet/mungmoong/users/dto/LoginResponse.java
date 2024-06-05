package com.mypet.mungmoong.users.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String userId;
    private String token;
    private String message;

    public LoginResponse(String userId, String token, String message) {
        this.userId = userId;
        this.token = token;
        this.message = message;
    }
}
