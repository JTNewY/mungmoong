package com.mypet.mungmoong.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UsersService userService;

    @GetMapping("/{page}")
    public String test(@PathVariable("page") String page) {
        return "/users/" + page;
    }   
    

    @PostMapping("/register")
    public String registerPro(Users user) throws Exception {
        int result = userService.join(user);

        if(result >0){
            return "redirect:/login";

        }
        
        return "redirect:/register?error";
    }
    

}
