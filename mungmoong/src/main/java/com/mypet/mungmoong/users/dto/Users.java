    package com.mypet.mungmoong.users.dto;

import java.util.Date;
import java.util.List;


import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.pet.dto.Pet;


import lombok.Data;

@Data
public class Users {
    private String userId;
    private String password;
    private String name;
    private Date birth;
    private String address;
    private String mail;
    private String phone;
    private Date regDate;
    private Date updDate;
    private int enabled;

    private Trainer trainer;
    private List<UserAuth> authList;

    private Pet pet;
}
