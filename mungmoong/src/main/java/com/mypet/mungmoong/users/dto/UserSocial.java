package com.mypet.mungmoong.users.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserSocial {
    private String userId;           // 사용자 ID
    private String socialId;         // 소셜 ID
    private String socialPlatform;   // 소셜 플랫폼
    private String name;             // 사용자 이름
    private String mail;             // 사용자 이메일
    private String picture;          // 사용자 프로필 사진 URL
    private Date linkedDate;         // 연결된 날짜
    private Date updatedDate;        // 업데이트된 날짜
}
