package com.mypet.mungmoong.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinRequest {
    private String userId;
    private String mail;
    private String name;

    public Users toUser() {
        Users user = new Users();
        user.setUserId(this.userId);
        user.setMail(this.mail);
        user.setName(this.name);
        return user;
    }
}
