package com.mypet.mungmoong.security;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.mapper.PetMapper;
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

    @Autowired
    private PetMapper petMapper;
    
    /**
     * 인증 성공 시 실행되는 메소드
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("로그인 인증 성공...");

        // 아이디 저장
        String rememberId = request.getParameter("remember-id"); // 아이디 저장 여부
        String username = request.getParameter("userId"); // 아이디
        log.info("rememberId : " + rememberId);
        log.info("id : " + username);

        // ✅ 아이디 저장 체크
        if (rememberId != null && rememberId.equals("on")) {
            Cookie cookie = new Cookie("remember-id", username);
            cookie.setMaxAge(60 * 60 * 24 * 7); // 유효기간 : 7일
            cookie.setPath("/"); // 쿠키 적용 경로 지정
            response.addCookie(cookie); // 응답에 쿠키 등록
        } else {
            Cookie cookie = new Cookie("remember-id", "");
            cookie.setMaxAge(0); // 유효기간 : 만료
            cookie.setPath("/"); // 쿠키 적용 경로 지정
            response.addCookie(cookie); // 응답에 쿠키 등록
        }

        // 인증된 사용자 정보 - (아이디/패스워드/권한)
        CustomUser loginUser = (CustomUser) authentication.getPrincipal();
        Users user = loginUser.getUser();
        log.info("--------------------------------------------------------");
        log.info("user : " + user);

        // userId를 가져와 훈련사 객체 가져옴
        String userId = user.getUserId();
        List<Pet> pets = petMapper.findPetsByUserId(userId);
        Trainer trainer = trainerMapper.select(userId);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId); // 여기에 userId를 저장
            session.setAttribute("pets", pets);

            if (pets != null && !pets.isEmpty()) {
                for (Pet pet : pets) {
                    log.info("Pet Information:");
                    log.info("Pet No: " + pet.getPetNo());
                    log.info("Pet ID: " + pet.getUserId());
                    log.info("Pet Name: " + pet.getPetname());
                    log.info("Pet Type: " + pet.getType());
                }
            } else {
                log.info("No pets found for user with ID: " + userId);
            }
        }

        // 훈련사 회원이면
        if (trainer != null) {
            user.setTrainer(trainer);
            request.getSession().setAttribute("trainerNo", trainer.getNo());
            log.info("세션에 저장된 훈련사 번호 : " + trainer.getNo());
        }
        request.getSession().setAttribute("user", user);
   
        log.info("아이디 : " + loginUser.getUsername());
        log.info("패스워드 : " + loginUser.getPassword()); // 보안상 노출❌
        log.info("권한 : " + loginUser.getAuthorities());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
