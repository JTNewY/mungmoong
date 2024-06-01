package com.mypet.mungmoong.trainermain.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.service.BoardService;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.CareerService;
import com.mypet.mungmoong.trainer.service.FileService;
import com.mypet.mungmoong.trainer.service.TrainerService;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("trainermain")
public class TrainerMainController {
    @Autowired
    UsersService usersService;
    @Autowired
    TrainerService trainerService;
    @Autowired
    CareerService careerService;
    @Autowired
    FileService fileService;
    @Autowired
    BoardService boardService;
  
    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Trainer> trainerList = trainerService.trainerList();

        model.addAttribute("trainerList", trainerList);
        return "/trainermain/list";
    }
       /**
     * 게시글 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String insert() {

        return "/trainermain/insert";
    }

      /**
     * 게시글 등록 처리
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/insert")
    public String insertPro(Trainer trainer) throws Exception {

        log.info(trainer.toString());

        // 데이터 요청
        int result = trainerService.insert(trainer);
        // 리다이렉트
        // ⭕ 데이터 처리 성공
        if( result > 0 ) {
            return "redirect:/trainermain/list";
        }
        // ❌ 데이터 처리 실패
        return "redirect:/trainermain/insert?error";  
    }

}

