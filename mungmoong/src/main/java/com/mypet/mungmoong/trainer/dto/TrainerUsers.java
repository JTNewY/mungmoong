package com.mypet.mungmoong.trainer.dto;

import java.util.Date;
import java.util.List;

import com.mypet.mungmoong.users.dto.UserAuth;

import lombok.Data;

@Data
public class TrainerUsers {
    // 아이디
    private String userId;

    // 패스워드
    private String password;

    // 이름
    private String name;

    // 생일
    private Date birth;

    // 주소
    private String address;

    // 이메일
    private String mail;

    // 핸드폰
    private String phone;

    // 등록일자
    private Date regDate;

    // 수정일자
    private Date updDate;

    private Integer role;           // role : 0(유저),1(훈련사),2(관리자)
 
    private int enabled;

    private List<UserAuth> authList;
}
