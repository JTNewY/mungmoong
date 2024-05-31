package com.mypet.mungmoong.config;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.mypet.mungmoong.security.LoginSuccessHandler;
import com.mypet.mungmoong.users.service.UserDetailServiceImpl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    // 스프링 시큐리티 설정 메소드
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // ✅ 인가 설정
        http.authorizeRequests(requests -> requests
                                            .antMatchers("/**").permitAll()
                                            .anyRequest().authenticated());

        // 🔐 폼 로그인 설정
        http.formLogin(login -> login
            .loginPage("/users/login")
            .loginProcessingUrl("/login")
            .usernameParameter("userId")
            .passwordParameter("password")
            .defaultSuccessUrl("/")
            .successHandler(loginSuccessHandler)
            .failureUrl("/loginError"));

        // ✅ 사용자 정의 인증 설정
        http.userDetailsService(userDetailServiceImpl);


        

        // 🔄 자동 로그인 설정
        http.rememberMe(me -> me
            .key("aloha")
            .tokenRepository(tokenRepository())
            .tokenValiditySeconds(60 * 60 * 24 * 7)
            .authenticationSuccessHandler(loginSuccessHandler)
            );

        return http.build();
    }

    /**
    * 🍃 자동 로그인 저장소 빈 등록
    */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl();
        repositoryImpl.setDataSource(dataSource);
        try {
            repositoryImpl.getJdbcTemplate().execute(JdbcTokenRepositoryImpl.CREATE_TABLE_SQL);
        } catch (BadSqlGrammarException e) {
            log.error("persistent_logins 테이블이 이미 존재합니다.");
        } catch (Exception e) {
            log.error("자동 로그인 테이블 생성 중 예외 발생", e);
        }
        return repositoryImpl;
    }
}

