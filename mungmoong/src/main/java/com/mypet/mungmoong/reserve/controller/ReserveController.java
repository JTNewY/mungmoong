package com.mypet.mungmoong.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.reserve.dto.Reserve;
import com.mypet.mungmoong.reserve.service.ReserveService;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.TrainerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller 
@RequestMapping("/reserve") 
public class ReserveController {
    //의존성 자동 주입
    @Autowired                 
    private ReserveService reserveService;
    @Autowired                 
    private TrainerService trainerService;



    /**
     * 예약목록 조회 화면
     */
    @GetMapping("/list")
    public String list(Model model)throws Exception {
        //데이터 요청
        List<Reserve> reserveList = reserveService.list();
        //데이터 등록
        model.addAttribute("reserveList", reserveList);
        //뷰페이지 등록
        return "/reserve/list"; 
    }
    /**
     * 예약 조회 화면
     */
    @GetMapping("/read")
    public String read(@RequestParam("date_no") int no, Model model) throws Exception {
        //데이터 요청
        Reserve reserve = reserveService.select(no);
        //모델 등록
        model.addAttribute("reserve", reserve);
        //뷰페이지 지정
        return "/reserve/read";
    }
    @GetMapping("/insert")
    public String insert(Model model
                        ,@RequestParam("date_no") String date
                        ,@RequestParam("trainerId") String trainerId) throws Exception {
        log.info("date : " + date);
        log.info("trainerId : " + trainerId);
        Trainer trainer = trainerService.select(trainerId);
        log.info("훈련사 정보 : " + trainer);
        
        model.addAttribute("trainer", trainer);
        return "/reserve/insert";
    }
/**
 * 예약 등록 처리
 */
    @PostMapping("/insert")
    public String insertPro(Reserve reserve) throws Exception {
        //데이터 요청
        int result = reserveService.insert(reserve);
        //리다이렉트
        //⭕데이터 처리 성공
        if (result > 0) {
            return "redirect:/reserve/list";
        }
        //❌데이터 처리 실패
        return "redirect:/reserve/insert?error";
    }
    /**
     * 게시글 수정화면
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/update")
     public String update(@RequestParam("date_no") int no, Model model) throws Exception {
         Reserve reserve = reserveService.select(no);
         model.addAttribute("reserve", reserve);
        return "/reserve/update";
    }
    
    @PostMapping("/update")
    public String updatePro(Reserve reserve) throws Exception {
       
        int result = reserveService.update(reserve);

        if (result > 0) {
            return "redirect:/reserve/list";
        }
        int no = reserve.getNo();
        return  "redirect:/reserve/update?no="+ no + "&error";
    }
    
    @PostMapping("/delete")
    public String delete(@RequestParam("date_no") int no) throws Exception {
       
        int result = reserveService.delete(no);
        if (result > 0) {
            return "redirect:/reserve/list";
        }
        
        return "redirect:/reserve/update?no=" + no + "&error";
    }
    
}