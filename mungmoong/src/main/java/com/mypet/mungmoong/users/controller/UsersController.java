package com.mypet.mungmoong.users.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.mypet.mungmoong.users.service.EmailService;
import com.mypet.mungmoong.users.service.UsersService;



@Controller("usersController")
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UsersService userService;

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
