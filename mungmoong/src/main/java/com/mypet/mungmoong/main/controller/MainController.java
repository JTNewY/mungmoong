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
