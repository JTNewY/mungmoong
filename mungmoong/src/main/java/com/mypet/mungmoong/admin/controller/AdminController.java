package com.mypet.mungmoong.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.service.PetService;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersService userService;

    @Autowired
    private PetService petService;

    /**
     * 관리자 회원정보 조회
     * 
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

    /**
     * 관리자 회원정보 상세 페이지
     * 
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info_read")
    public String read(@RequestParam("userId") String userId, Model model) throws Exception {
        Users users = userService.select(userId);
        model.addAttribute("users", users);
        return "/admin/admin_info_read";
    }

    /**
     * 관리자 회원정보 수정 화면
     * 
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info_read_update")
    public String update(@RequestParam("userId") String userId, Model model) throws Exception {

        // 데이터 요청
        Users users = userService.select(userId);

        // 모델 등록
        model.addAttribute("users", users);

        return "/admin/admin_info_read_update";
    }

    /**
     * 게시글 수정 처리
     * 
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/admin_info_read_update")
    public String updatePro(Users user) throws Exception {
        int result = userService.update(user);

        if (result > 0) {
            return "redirect:/admin/admin_info";
        }
        String userId = user.getUserId();
        return "redirect:/admin/admin_info_read_update?userId=" + userId + "&error";
    }

    /**
     * 
     * @param petNo
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/admin_info_read_pet")
    public String read(HttpSession session, Model model) throws Exception {
        Users loginUser = (Users) session.getAttribute("user");
        String userId = loginUser.getUserId();

        List<Pet> petList = petService.findPetByUserId(userId);

        model.addAttribute("petList", petList);
        return "/admin/admin_info_read_pet";
    }

    /**
     * 관리자 훈련사 정보 삭제
     * 
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("userId") String userId) throws Exception {
        log.info("userId : " + userId);
        int result = userService.delete(userId);

        if (result > 0) {
            return "redirect:/admin/admin_info";
        }

        return "redirect:/admin/admin_info_read_update?userId=" + userId + "&error";
    }

}
