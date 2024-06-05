package com.mypet.mungmoong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mypet.mungmoong.users.dto.NaverUserApi;
import com.mypet.mungmoong.users.dto.NaverUserApiImpl;

@Configuration
public class AppConfig {

    @Bean
    public NaverUserApi naverUserApi() {
        return new NaverUserApiImpl();
    }
}
