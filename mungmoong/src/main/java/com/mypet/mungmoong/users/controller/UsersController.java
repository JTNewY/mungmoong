package com.mypet.mungmoong.users.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;



@Controller("usersController")
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UsersService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @GetMapping("/{page}")
    public String test(@PathVariable("page") String page) {
        return "/users/" + page;
    }   
 

    @PostMapping("/register")
    public String registerUser(Users user, Pet pet, String userId) throws Exception {
       user.setUserId(userId);
       pet.setUserId(userId);

        user.setPet(pet);
        
        int result = userService.join(user);
        if (result > 0) {
            return "redirect:/users/login";
        }
        return "redirect:/register?error";
    }

    /**
     * 자동로그인
     * @param username
     * @param password
     * @param keepLoggedIn
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) boolean keepLoggedIn, HttpServletRequest request, HttpServletResponse response) {
        // 사용자 인증 로직
        UsernamePasswordAuthenticationToken authReq
        = new UsernamePasswordAuthenticationToken(username, password);
    Authentication auth = authenticationManager.authenticate(authReq);
        
        if (auth.isAuthenticated()) {
            // 로그인 성공 후 로그인 상태 유지를 위해 쿠키 설정
            Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
            
            if (keepLoggedIn) {
                // 로그인 상태 유지 선택 시, 쿠키 만료 시간 설정
                cookie.setMaxAge(7 * 24 * 60 * 60); // 쿠키를 7일 동안 유지
            }
            
            // 보안 설정
            cookie.setHttpOnly(true); // JavaScript를 통한 쿠키 접근 방지
            if (request.isSecure()) {
                cookie.setSecure(true); // HTTPS를 사용할 경우에만 쿠키 전송
            }
    
            // 응답에 쿠키 추가
            response.addCookie(cookie);
            
            // 인증 상태를 보안 컨텍스트에 저장
            SecurityContextHolder.getContext().setAuthentication(auth);
            
            return "redirect:/userProfile"; // 로그인 후 리디렉트할 페이지
        }
        
        return "loginError"; // 로그인 실패 시
    }

    /**
     * 아이디 중복 검사
     * @param username
     * @return
     * @throws Exception
     */
    @GetMapping("/register/check/{userId}")
    public ResponseEntity<Boolean> userCheck(@PathVariable("userId") String username) throws Exception {
        //log.info("아이디 중복 확인 : " + username);
        Users user = userService.select(username);
        // 아이디 중복
        if( user != null ) {
            //log.info("중복된 아이디 입니다 - " + username);
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        // 사용 가능한 아이디입니다.
        //log.info("사용 가능한 아이디 입니다." + username);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

private Map<String, Integer> otpStorage = new HashMap<>();

    @PostMapping("/register/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (!email.contains("@")) {
            return ResponseEntity.badRequest().body("Invalid email format");
        }
        int otp = new Random().nextInt(999999);
        otpStorage.put(email, otp);
        try {
            emailService.sendEmail(email, "Verify your email", "Your OTP is: " + otp);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in sending OTP: " + e.getMessage());
        }
    }
    
    @PostMapping("/register/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        try {
            int otp = Integer.parseInt(payload.get("otp"));
            if (otpStorage.getOrDefault(email, -1) == otp) {
                otpStorage.remove(email);
                return ResponseEntity.ok("Email verified successfully");
            } else {
                return ResponseEntity.badRequest().body("Invalid OTP");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("OTP must be a number");
        }
    }



} // 끝
