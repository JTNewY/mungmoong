package com.mypet.mungmoong.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/{page}")
    public String test(@PathVariable("page") String page) {
        return "users/" + page;
    }

}
