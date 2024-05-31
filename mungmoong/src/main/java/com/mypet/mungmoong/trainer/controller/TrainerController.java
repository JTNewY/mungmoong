package com.mypet.mungmoong.trainer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.trainer.dto.Career;
import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.CareerService;
import com.mypet.mungmoong.trainer.service.CertificateService;
import com.mypet.mungmoong.trainer.service.FileService;
import com.mypet.mungmoong.trainer.service.TrainerService;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;



/**
 *  /board 경로로 요청 왔을 때 처리
 * [GET]  - /trainer/list   : 훈련사 목록 화면
 * [GET]  - /trainer/read   : 훈련사 조회 화면
 * [GET]  - /trainer/insert : 훈련사 등록 화면
 * [POST] - /trainer/insert : 훈련사 등록 처리
 * [GET]  - /trainer/update : 훈련사 수정 화면
 * [POST] - /trainer/update : 훈련사 수정 처리 
 * [POST] - /trainer/delete : 훈련사 삭제 처리
 */
@Slf4j                                  // 로그 어노테이션
@Controller                             // 컨트롤러 스프링 빈으로 등록 -> 여러가지 매핑 사용 가능
@RequestMapping("/trainer")       // 상위 경로를 먼저 지정해주고 싶을 때
                                        // 클래스 레벨 요청 경로 매핑 - /board~ 경로의 요청을 처리
public class TrainerController {


    @GetMapping("/{page}")
    public String test(@PathVariable("page") String page) {
        return "/trainer/" + page;
    } 
    
    // ⭐ 데이터 요청과 화면 출력
    // Controller --> Service (데이터 요청)
    // Controller <-- Service (데이터 전달)
    // Controller --> Model   (모델 등록)
    // View <-- Model         (데이터 출력)
    @Autowired  // 의존성 자동 주입
                // BoardServiceImpl을 @Service로 빈 등록해놨기 때문에, 
                // 의존성 자동 주입을 해서 가져올 수 있다.
    private TrainerService trainerService;      // @Service를 --Impl에 등록

    @Autowired
    private UsersService usersService;


    @Autowired
    private FileService fileService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private CertificateService certificateService;


    /**
     * 훈련사 정보 조회 (경력, 소개, 자격증)
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/info")
    public String select(@RequestParam("userId") String userId, Model model) throws Exception {
        Trainer trainer = trainerService.select(userId);
        int trainerNo = trainer.getNo();
        // Career career = careerService.select(trainerNo);
        List<Career> careerList = careerService.select(trainerNo);
        List<Certificate> certificateList = certificateService.select(trainerNo);
        model.addAttribute("trainer", trainer);
        model.addAttribute("careerList", careerList);
        model.addAttribute("certificateList", certificateList);
        return "/trainer/info";
    }


    /**
     * 훈련사 정보 등록 처리
     * @param trainer
     * @return
     * @throws Exception 
     */
    @PostMapping("/join_data")
    public String joinDataPro(HttpSession session
                             ,@ModelAttribute Trainer trainer) throws Exception {
        log.info(":::::::::::::::::::::::::::::::::::::::::");
        log.info("::::::::::::::: trainer :::::::::::::::::");
        log.info(" trainer : " + trainer);
        Users user = (Users) session.getAttribute("user");
        String userId = user.getUserId();
        trainer.setUserId(userId);

        // 데이터 요청
        int result = trainerService.insert(trainer);
        
        // 데이터 처리 성공
        if(result > 0) {
            Users newUser = usersService.select(userId);
            session.setAttribute("user", newUser);
            return "redirect:/trainer/join_data";
        }
        
        // 데이터 처리 실패
        return "redirect:/trainer/join_data?error";
    }
    
    
    /**
     * 훈련사 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String insert() {

        return "/trainer/board/insert";
    }

    @PostMapping("/insert")
    public String insertPro(Trainer trainer) throws Exception {

        log.info(trainer.toString());

        // 데이터 요청
        int result = trainerService.insert(trainer);
        // 리다이렉트
        // ⭕ 데이터 처리 성공
        if(result > 0) {
            return "redirect:/trainer/board/list";
        }
        // ❌ 데이터 처리 실패
        return "redirect:/trainer/board/insert?error";
    }

    /**
     * 훈련사 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/update")
    public String update(@RequestParam("userId") String userId, Model model, Files file) throws Exception {

        // 데이터 요청
        Trainer trainer = trainerService.select(userId);

        // 파일 목록 요청
        file.setParentTable("board");
        // file.setParentNo(userId);
        List<Files> fileList = fileService.listByParent(file);

        // 모델 등록
        model.addAttribute("trainer", trainer);
        model.addAttribute("fileList", fileList);

        return "/trainer/board/update";
    }

    /**
     * 훈련사 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    // @PostMapping("/update")
    // public String updatePro(Trainer trainer) throws Exception {
    //     int result = trainerService.update(trainer);
    //     if(result > 0) {
    //         return "redirect:/trainer/board/list";
    //     }
    //     // String no = trainer.getNo();
    //     // return "redirect:/trainer/board/update?no=" + no + "&error";
    // }
    

    // @PostMapping("/delete")
    // public String delete(@RequestParam("no") int no) throws Exception {
    //     // 글 삭제 요청
    //     int result = TrainerService.delete(no);
    //     // 글 삭제가 되면, 첨부파일도 같이 삭제
    //     if(result > 0) {

    //         // 첨부파일 삭제
    //         Files file = new Files();
    //         file.setParentTable("board");
    //         file.setParentNo(no);
    //         fileService.deleteByParent(file);
            
    //         return "redirect:/trainer/board/list";
    //     }
    //     return "redirect:/trainer/board/update?no=" + no + "&error";
    // }
    
}
