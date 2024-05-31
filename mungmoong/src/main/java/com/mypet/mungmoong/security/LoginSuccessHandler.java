package com.mypet.mungmoong.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.mapper.TrainerMapper;
import com.mypet.mungmoong.users.dto.CustomUser;
import com.mypet.mungmoong.users.dto.Users;

import lombok.extern.slf4j.Slf4j;

/**
 * ✅ 로그인 성공 처리 클래스
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Autowired
    private TrainerMapper trainerMapper;
    
    /**
     * 인증 성공 시 실행되는 메소드
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
                                      , HttpServletResponse response
                                      , Authentication authentication) throws ServletException, IOException {
        
        log.info("로그인 인증 성공...");

        // 아이디 저장
        String rememberId = request.getParameter("remember-id");    // 아이디 저장 여부
        String username = request.getParameter("id");               // 아이디
        log.info("rememberId : " + rememberId);
        log.info("id : " + username);


        // ✅ 아이디 저장 체크
        if( rememberId != null && rememberId.equals("on") ) {
            Cookie cookie = new Cookie("remember-id", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);         // 유효기간 : 7일
            cookie.setPath("/");                    // 쿠키 적용 경로 지정
            response.addCookie(cookie);                 // 응답에 쿠키 등록
        }

        // 🟩 아이디 저장 체크 ❌
        else {
            Cookie cookie = new Cookie("remember-id", "");
            cookie.setMaxAge(0);                // 유효기간 : 만료
            cookie.setPath("/");                   // 쿠키 적용 경로 지정
            response.addCookie(cookie);                // 응답에 쿠키 등록
        }

        // 인증된 사용자 정보 - (아이디/패스워드/권한)
        // User user = (User) authentication.getPrincipal();
        CustomUser loginUser = (CustomUser) authentication.getPrincipal();
        Users user = loginUser.getUser();

        // userId를 가져와 훈련사 객체 가져옴
        String userId = user.getUserId();
        Trainer trainer = trainerMapper.select(userId);
        
        // 훈련사 회원이면
        if( trainer != null ) {
            user.setTrainer(trainer);
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        log.info("아이디 : " + loginUser.getUsername());
        log.info("패스워드 : " + loginUser.getPassword());       // 보안상 노출❌
        log.info("권한 : " + loginUser.getAuthorities());    
        
        super.onAuthenticationSuccess(request, response, authentication);
    }


    
}