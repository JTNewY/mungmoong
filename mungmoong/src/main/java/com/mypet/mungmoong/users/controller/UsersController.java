package com.mypet.mungmoong.users.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.mapper.UsersMapper;
import com.mypet.mungmoong.users.service.EmailService;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

 


    @GetMapping("/{page}")
    public String test(@PathVariable("page") String page) {
        return "/users/" + page;
    }   
        
    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String rememberedUserId = null;
        boolean rememberUserId = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("remember-id".equals(cookie.getName())) {
                    rememberedUserId = cookie.getValue();
                    rememberUserId = true;
                    break; // 쿠키를 찾으면 루프를 종료합니다.
                }
            }
        }
        
        model.addAttribute("userValue", rememberedUserId); // 사용자 ID를 모델에 추가
        model.addAttribute("rememberId", rememberUserId); // 아이디 저장 여부를 모델에 추가
        return "users/login"; // 로그인 뷰 이름 반환
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

    @PostMapping("/findId")
    public String findUserId(@RequestParam("name") String name, @RequestParam("mail") String mail, Model model) throws Exception {
        Users user = userService.findId(name, mail);

        if (user != null && user.getUserId() != null) {
            System.out.println("찾은 userId: " + user.getUserId());
            model.addAttribute("foundId", user.getUserId());
            return "/users/findIdCheck"; // 템플릿으로 직접 이동
        } else {
            System.out.println("유저 ID를 찾을 수 없음");
            model.addAttribute("errorMessage", "해당 아이디와 이메일을 찾을 수 없습니다.");
            return "/users/findId"; // 에러 메시지를 포함한 폼으로 다시 이
        }
    }
    @GetMapping("/users/findIdCheck")
    public String showResult(@RequestParam("foundId") String foundId, Model model) {
        System.out.println("쿼리 파라미터에서 찾은 foundId: " + foundId);
        if (foundId == null || foundId.isEmpty()) {
            System.out.println("쿼리 파라미터에서 foundId를 찾을 수 없습니다.");
            return "redirect:/"; // 아이디를 찾을 수 없을 경우 홈으로 리다이렉트
        }
        model.addAttribute("foundId", foundId);
        return "findIdCheck";
    }


    @PostMapping("/findPw")
    public String findPWPro(@RequestParam("userId") String userId, @RequestParam("mail") String mail, Model model) throws Exception {
        Users user = userService.findPw(userId, mail);
    
        if (user != null && user.getPassword() != null) {
            System.out.println("찾은 userPw " + user.getPassword());
            model.addAttribute("foundId", user.getPassword());
            model.addAttribute("userId", userId); // userId 추가
            model.addAttribute("mail", mail); // mail 추가
            return "/users/changePw"; // 템플릿으로 직접 이동
        } else {
            System.out.println("유저 ID를 찾을 수 없음");
            model.addAttribute("errorMessage", "해당 유저 정보를 찾을수 없습니다.");
            return "/users/findId"; // 에러 메시지를 포함한 폼으로 다시 이동
        }
    }

   
    @PostMapping("/resetPw")
    public String resetPassword(@RequestParam("userId") String userId, @RequestParam("mail") String mail,
                                @RequestParam("password") String password, Model model) throws Exception {
        // 비밀번호 해싱
        String hashedPassword = passwordEncoder.encode(password);

        // Hashed password를 실제 요청 파라미터인 password에 할당
        int result = userService.updatePassword(userId, mail, hashedPassword);
        if (result > 0) {
            model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
            return "redirect:../"; // 로그인 페이지로 리디렉션
        } else {
            model.addAttribute("errorMessage", "비밀번호 변경에 실패했습니다.");
            return "users/changePw";
        }
    }
    
    
    // #################################### 회원가입 ##############################################
    
    private Map<String, Integer> otpStorage = new HashMap<>();

    @PostMapping("/register/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (!email.contains("@")) {
            return ResponseEntity.badRequest().body("이메일 형식이 잘못되었습니다.");
        }
        int otp = new Random().nextInt(999999);
        otpStorage.put(email, otp);
        try {
            emailService.sendEmail(email, "[멍뭉] 이메일을 인증해주세요. ", "회원가입을 위해 이메일을 인증해주세요.\n" +"이메일 OTP번호: " + otp);
            return ResponseEntity.ok("인증번호를 발송하였습니다.");
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

                return ResponseEntity.ok("이메일인증 성공하였습니다.");
            } else {
                return ResponseEntity.badRequest().body("잘못된 인증번호입니다.");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("OTP는 숫자여야 합니다.");
        }
    }

    // ################################# 아이디 찾기 #####################################################
    @PostMapping("/find/sendOtp")
    public ResponseEntity<?> sendOtp2(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        if (!email.contains("@")) {
            return ResponseEntity.badRequest().body("이메일 형식이 잘못되었습니다.");
        }
        int otp = new Random().nextInt(999999);
        otpStorage.put(email, otp);
        try {
            emailService.sendEmail(email, "[멍뭉] 아이디/비밀번호 찾기 이메일을 인증해주세요. ", "이메일을 인증해주세요.\n" +"이메일 OTP번호: " + otp);
            return ResponseEntity.ok("인증번호를 발송하였습니다.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in sending OTP: " + e.getMessage());
        }
    }

    @PostMapping("/find/verifyOtp")
    public ResponseEntity<?> verifyOtp2(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        try {
            int otp = Integer.parseInt(payload.get("otp"));
            if (otpStorage.getOrDefault(email, -1) == otp) {
                otpStorage.remove(email);

                return ResponseEntity.ok("이메일인증 성공하였습니다.");
            } else {
                return ResponseEntity.badRequest().body("잘못된 인증번호입니다.");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("OTP는 숫자여야 합니다.");
        }
    }
} // 끝
