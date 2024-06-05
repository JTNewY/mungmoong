package com.mypet.mungmoong.users.dto;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NaverUserApiImpl implements NaverUserApi {
    private static final String NAVER_USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";

    @Override
    public ResponseEntity<String> getUserInfo(Map<String, String> headers) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(NAVER_USER_INFO_URL, String.class, headers);
    }
}