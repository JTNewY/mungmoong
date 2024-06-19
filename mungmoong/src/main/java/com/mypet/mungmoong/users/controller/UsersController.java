package com.mypet.mungmoong.users.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.users.dto.SocialUserResponse;
import com.mypet.mungmoong.users.dto.UserJoinRequest;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.EmailService;
import com.mypet.mungmoong.users.service.LoginService;
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

    @Autowired
    private LoginService loginService;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

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
                    break;
                }
            }
        }

        model.addAttribute("userValue", rememberedUserId);
        model.addAttribute("rememberId", rememberUserId);
        return "users/login";
    }

    @GetMapping("/index")
    public String myPets(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<Pet> pets = (List<Pet>) session.getAttribute("pets");
        model.addAttribute("pets", pets);
        return "users/index";
    }

    @GetMapping("/using")
    public String myPets2(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<Pet> pets = (List<Pet>) session.getAttribute("pets");
        model.addAttribute("pets", pets);
        return "users/using";
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
        Users user = userService.select(username);
        if (user != null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/findId")
    public String findUserId(@RequestParam("name") String name, @RequestParam("mail") String mail, Model model) throws Exception {
        Users user = userService.findId(name, mail);

        if (user != null && user.getUserId() != null) {
            model.addAttribute("foundId", user.getUserId());
            return "/users/findIdCheck";
        } else {
            model.addAttribute("errorMessage", "해당 아이디와 이메일을 찾을 수 없습니다.");
            return "/users/findId";
        }
    }

    @GetMapping("/users/findIdCheck")
    public String showResult(@RequestParam("foundId") String foundId, Model model) {
        if (foundId == null || foundId.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("foundId", foundId);
        return "findIdCheck";
    }

    @PostMapping("/findPw")
    public String findPWPro(@RequestParam("userId") String userId, @RequestParam("mail") String mail, Model model) throws Exception {
        Users user = userService.findPw(userId, mail);

        if (user != null && user.getPassword() != null) {
            model.addAttribute("foundId", user.getPassword());
            model.addAttribute("userId", userId);
            model.addAttribute("mail", mail);
            return "/users/changePw";
        } else {
            model.addAttribute("errorMessage", "해당 유저 정보를 찾을 수 없습니다.");
            return "/users/findId";
        }
    }

    @PostMapping("/resetPw")
    public String resetPassword(@RequestParam("userId") String userId, @RequestParam("mail") String mail,
                                @RequestParam("password") String password, Model model) throws Exception {
        String hashedPassword = passwordEncoder.encode(password);
        int result = userService.updatePassword(userId, mail, hashedPassword);
        if (result > 0) {
            model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
            return "redirect:../";
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
            emailService.sendEmail(email, "[멍뭉] 이메일을 인증해주세요. ", "회원가입을 위해 이메일을 인증해주세요.\n" + "이메일 OTP번호: " + otp);
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
            emailService.sendEmail(email, "[멍뭉] 아이디/비밀번호 찾기 이메일을 인증해주세요. ", "이메일을 인증해주세요.\n" + "이메일 OTP번호: " + otp);
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

    // ################################# 네이버 로그인 ##################################################

    @GetMapping("/naver-login")
    public String naverLogin(@AuthenticationPrincipal OAuth2AuthenticationToken authentication) throws Exception {
        if (authentication == null) {
            throw new IllegalArgumentException("Authentication information is missing");
        }

        String registrationId = authentication.getAuthorizedClientRegistrationId();
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                registrationId, authentication.getName());

        if (authorizedClient != null) {
            String accessToken = authorizedClient.getAccessToken().getTokenValue();
            SocialUserResponse userInfo = userService.getUserInfo(accessToken);
            userService.joinUser(new UserJoinRequest(userInfo.getUserId(), userInfo.getMail(), userInfo.getName()));
        }

        return "redirect:/";
    }

    // ################################# 내 정보 수정 #####################################################
    @GetMapping("/update")
    public String showUpdateForm(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login";
        }

        try {
            Users user = userService.select(userId);
            model.addAttribute("user", user);
            return "users/update"; // update.html 템플릿
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/users/login";
        }
    }

    @PostMapping("/update")
    public String updateUser(Users user, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login";
        }

        user.setUserId(userId); // 세션의 userId를 설정
     
        try {
            userService.update(user);
            return "redirect:/users/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/users/update?error";
        }
    }


    @PostMapping("/myupdate")
    public String myupdateUser(Users user, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login";
        }
    
        user.setUserId(userId); // 세션의 userId를 설정
    
        try {
            // 기존 사용자 정보를 가져와 역할을 설정
            Users existingUser = userService.select(userId);
            if (existingUser == null) {
                return "redirect:/users/update?error=notfound";
            }
    
            // 기존 역할 값을 유지
            user.setRole(existingUser.getRole());
    
            userService.Myupdate(user);
    
            // 사용자 정보를 다시 가져와 세션에 업데이트
            Users updatedUser = userService.select(userId);
            session.setAttribute("user", updatedUser);
    
            return "redirect:/users/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/users/update?error";
        }
    }


}
