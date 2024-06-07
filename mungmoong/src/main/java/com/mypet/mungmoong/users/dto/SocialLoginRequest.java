package com.mypet.mungmoong.users.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SocialLoginRequest {
    @NotEmpty(message = "Access token is required")
    private String accessToken;

    public SocialLoginRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
