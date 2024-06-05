package com.mypet.mungmoong.users.dto;

import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface NaverUserApi {
    ResponseEntity<String> getUserInfo(Map<String, String> headers);
}
