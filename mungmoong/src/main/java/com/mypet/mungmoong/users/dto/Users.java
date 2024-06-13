    package com.mypet.mungmoong.users.dto;

import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;


import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.pet.dto.Pet;



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
    private int enabled;
    private int role;
    private String gender;



    private Trainer trainer;
    private List<UserAuth> authList;
    private List<Pet> petList;

    private Pet pet;

    public Users() {
    
    }

    public Users(String userId, String mail, String name) {
        this.userId = userId;
        this.mail = mail;
        this.name = name;
    }
    // ############################## 06-14 수정 ##############################

    // enabled 필드의 게터 메서드 수정
    public int getEnabled() {
        return enabled;
    }

    // enabled 필드의 세터 메서드
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


    }