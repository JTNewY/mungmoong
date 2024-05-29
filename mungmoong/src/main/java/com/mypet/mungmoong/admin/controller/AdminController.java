package com.mypet.mungmoong.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersService userService;


    /**
     * 관리자 회원정보 조회
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info")
    public String list(Model model) throws Exception {

        List<Users> usersList = userService.list();
        log.info(usersList.toString());
        model.addAttribute("usersList", usersList);

        return "/admin/admin_info";
    }

    @GetMapping("/admin_info_read")
    public String read(@RequestParam("userId") String username, Model model) throws Exception {
        Users users = userService.select("username");
        model.addAttribute("users", users);
        return "/admin/admin_info_read";
    }



}
