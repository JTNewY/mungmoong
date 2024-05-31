package com.mypet.mungmoong.trainer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



import groovy.util.logging.Slf4j;

@Slf4j
@Controller
@RequestMapping("/trainermain")
public class TrainermainController {
    @GetMapping("/{page}")
    public String TrainermainController(@PathVariable("page") String page) {
        return "/users/" + page;
    }   
    
}
