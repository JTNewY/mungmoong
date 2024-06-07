package com.mypet.mungmoong.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverUserResponse {
    private Response response;

    @Getter
    @Setter
    public static class Response {
        private String id;
        private String email;
        private String name;
    }
}
