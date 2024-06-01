package com.mypet.mungmoong.main.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mypet.mungmoong.users.dto.CustomUser;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class MainController {

    @GetMapping("")
    public String home(Principal principal
                     ,Authentication auth) {
        log.info("메인");
        log.info(":::::::::: 메인 ::::::::::");
        log.info("principal  : " + principal);
        
        if(principal != null ) {
            CustomUser loginUser = (CustomUser) auth.getPrincipal();
            String userId = loginUser.getUser().getUserId(); 
            String address = loginUser.getUser().getAddress();
            
            // String userId = principal.getName();
            log.info("auth  : " + auth.getPrincipal());
            log.info("로그인 아이디 : " + userId);
            log.info("주소 : " + address);
        }

        return "index";
    }
    @GetMapping("introduce")
    public String intro(Principal principal
                     ,Authentication auth) {
        log.info("메인");
        log.info(":::::::::: 메인 ::::::::::");
        log.info("principal  : " + principal);
        
        if(principal != null ) {
            CustomUser loginUser = (CustomUser) auth.getPrincipal();
            String userId = loginUser.getUser().getUserId(); 
            String address = loginUser.getUser().getAddress();
            
            // String userId = principal.getName();
            log.info("auth  : " + auth.getPrincipal());
            log.info("로그인 아이디 : " + userId);
            log.info("주소 : " + address);
        }

        return "introduce";
    }
    @GetMapping("trainer")
    public String trainer(Principal principal
                     ,Authentication auth) {
        log.info("메인");
        log.info(":::::::::: 메인 ::::::::::");
        log.info("principal  : " + principal);
        
        if(principal != null ) {
            CustomUser loginUser = (CustomUser) auth.getPrincipal();
            String userId = loginUser.getUser().getUserId(); 
            String address = loginUser.getUser().getAddress();
            
            // String userId = principal.getName();
            log.info("auth  : " + auth.getPrincipal());
            log.info("로그인 아이디 : " + userId);
            log.info("주소 : " + address);
        }

        return "trainer";
    }
    @GetMapping("reserve")
    public String reserve(Principal principal
                     ,Authentication auth) {
        log.info("메인");
        log.info(":::::::::: 메인 ::::::::::");
        log.info("principal  : " + principal);
        
        if(principal != null ) {
            CustomUser loginUser = (CustomUser) auth.getPrincipal();
            String userId = loginUser.getUser().getUserId(); 
            String address = loginUser.getUser().getAddress();
            
            // String userId = principal.getName();
            log.info("auth  : " + auth.getPrincipal());
            log.info("로그인 아이디 : " + userId);
            log.info("주소 : " + address);
        }

        return "reserve/list";
    }
    @GetMapping("QnA")
    public String QnA(Principal principal
                     ,Authentication auth) {
        log.info("메인");
        log.info(":::::::::: 메인 ::::::::::");
        log.info("principal  : " + principal);
        
        if(principal != null ) {
            CustomUser loginUser = (CustomUser) auth.getPrincipal();
            String userId = loginUser.getUser().getUserId(); 
            String address = loginUser.getUser().getAddress();
            
            // String userId = principal.getName();
            log.info("auth  : " + auth.getPrincipal());
            log.info("로그인 아이디 : " + userId);
            log.info("주소 : " + address);
        }

        return "QnA";
    }
    @GetMapping("trainermain")
    public String trainerMain(Principal principal
                     ,Authentication auth) {
        log.info("메인");
        log.info(":::::::::: 메인 ::::::::::");
        log.info("principal  : " + principal);
        
        if(principal != null ) {
            CustomUser loginUser = (CustomUser) auth.getPrincipal();
            String userId = loginUser.getUser().getUserId(); 
            String address = loginUser.getUser().getAddress();
            
            // String userId = principal.getName();
            log.info("auth  : " + auth.getPrincipal());
            log.info("로그인 아이디 : " + userId);
            log.info("주소 : " + address);
        }

        return "trainermain";
    }
    // @GetMapping("/{page}")
    // public String test(@PathVariable("page") String page) {
    //     return "/" + page;
    // }

    // @GetMapping("/{domain}/{page}")
    // public String test(@PathVariable("domain") String domain
    //                   ,@PathVariable("page") String page) {
    //     return domain + "/" + page;
    // }
    
}
