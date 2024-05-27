package com.mypet.mungmoong.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration                  // 스프링 빈 설정 클래스로 지정
@EnableWebSecurity              // 스프링 시큐리티 설정 빈으로 등록
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;      // application.properties 에 정의한 DB 정보

    
    // 기본 설정
    // - 인메모리 방식 인증
    // - JDBC 인증 방식 인증

    /**
     * ⭐ 인메모리 방식 인증
     * - 기본 사용자를 메모리에 등록
     * - user  / 123456
     * - admin / 123456
     * @return
     */
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.builder()
    //                            .username("user")        // 아이디
    //                            .password(passwordEncoder().encode("123456"))      // 패스워드
    //                            .roles("USER")           // 권한
    //                            .build();

    //     UserDetails admin = User.builder()
    //                            .username("admin")       // 아이디
    //                            .password(passwordEncoder().encode("123456"))      // 패스워드
    //                            .roles("USER", "ADMIN")  // 권한
    //                            .build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }

    // JDBC 인증 방식
    // ✅ 데이터 소스 (URL, ID, PW) - application.properties
    // ✅ SQL 쿼리 등록
    //      ⭐ 사용자 인증 쿼리
    //      ⭐ 사용자 권한 쿼리
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager 
                = new JdbcUserDetailsManager(dataSource);

        // 사용자 인증 쿼리
        String sql1 = " SELECT user_id as username, user_pw as password "
                    + " FROM user "
                    + " WHERE user_id = ? "
                    ;
        // 사용자 권한 쿼리
        String sql2 = " SELECT user_id as username, auth "
                    + " FROM user_auth "
                    + " WHERE user_id = ? "
                    ;
        userDetailsManager.setUsersByUsernameQuery(sql1);
        userDetailsManager.setAuthoritiesByUsernameQuery(sql2);
        return userDetailsManager;
    }




    /**
     * 🍃 AuthenticationManager 빈 등록
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(
                                AuthenticationConfiguration authenticationConfiguration) 
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 🍃 암호화 방식 빈 등록
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    

}
