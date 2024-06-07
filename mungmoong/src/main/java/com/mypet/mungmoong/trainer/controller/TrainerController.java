package com.mypet.mungmoong.trainer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.mypet.mungmoong.trainer.service.ScheduleService;
import com.mypet.mungmoong.trainer.service.TrainerService;
import com.mypet.mungmoong.users.dto.Users;
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


    private Logger logger = LoggerFactory.getLogger(TrainerController.class);

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
    private FileService fileService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private ScheduleService scheduleService;



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
        List<Career> careerList = careerService.select(userId);
        List<Certificate> certificateList = certificateService.listByUserId(userId);
        model.addAttribute("trainer", trainer);
        model.addAttribute("careerList", careerList);
        model.addAttribute("certificateList", certificateList);
        return "/trainer/info";
    }

    @PostMapping("/join_data")
    public String insertPro(@ModelAttribute Trainer trainer, HttpSession session, Model model) {
        try {
            Users user = (Users) session.getAttribute("user");

            if (user == null) {
                return "redirect:/login";
            }
            
            trainer.setUserId(user.getUserId());


            trainer.setCareerList(trainer.toCareerList());
            trainer.setCertificateList(trainer.toCertificateList());
            log.info("trainer 로그조회 : " + trainer);

            int result = trainerService.insert(trainer);

            if (result > 0) {
                return "redirect:/";
            }
        } catch (Exception e) {
            log.error("Error occurred while processing trainer data", e);
            model.addAttribute("errorMessage", "Error occurred while processing trainer data: " + e.getMessage());
        }

        return "redirect:/trainer/join_data?error";
    }


    /**
     * 훈련사 수정 화면
     */
    @GetMapping("/info_update")
    public String update(@RequestParam("userId") String userId, Model model, Files file, HttpSession session) throws Exception {
        Trainer trainer = trainerService.select(userId);
        List<Career> careerList = careerService.select(userId);  // select -> listByUserId
        List<Certificate> certificateList = certificateService.listByUserId(userId);
        List<Files> fileList = fileService.listByParent(file);
        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if(trainerNo == null) {
            log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
        }

        log.info("--------------------------------------------------------------");
        log.info(careerList.toString());
        
        file.setParentTable("trainer");
        file.setParentTable("certificate");
        
        model.addAttribute("trainer", trainer);
        model.addAttribute("trainerNo", trainerNo);
        model.addAttribute("careerList", careerList);
        model.addAttribute("certificateList", certificateList);
        model.addAttribute("fileList", fileList);

        return "/trainer/info_update";
    }


    /**
     * 훈련사 수정 처리
     * @param trainer
     * @param session
     * @param model
     * @throws Exception
     */
    @PostMapping("/info_update")
    public String updatePro(Trainer trainer, HttpSession session) throws Exception {
        List<Career> careerList = trainer.toCareerList();
        List<Certificate> certificateList = trainer.toCertificateList();
        List<Files> filesList = fileService.list();

        log.info("--------------------------------");
        log.info(careerList.toString());
        log.info("트레이너 번호가 뭘까요 : " + trainer.getNo());

        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if (trainerNo == null) {
            log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
            return "redirect:/trainer/info_update?userId=" + trainer.getUserId() + "&error=session";
        }
        log.info("세션에서 가져온 트레이너 번호 : " + trainerNo);


            for (Career career : careerList) {
                career.setTrainerNo(trainerNo);
                log.info("trainerNo : " + trainerNo);

                int result = 0;
                if (career.getNo() > 0) {
                    result = careerService.update(career);
                    log.info("수정했다!!");
                } else {
                    career.setTrainerNo(trainerNo);
                    result = careerService.insert(career);
                    log.info("등록했다");
                }

                if (result > 0) {
                    log.info("성공했다");
                } else {
                    log.info(career.toString());
                    log.info("망했다");
                }
            }

            for (Certificate certificate : certificateList) {
                certificate.setTrainerNo(trainerNo);
                log.info("trainerNo : " + trainerNo);

                int result = 0;
                if (certificate.getNo() > 0) {
                    result = certificateService.update(certificate);
                    log.info(";;;;;;;;자격증 이미지update;;;;;;;;; : " + filesList.toString());
                    log.info("자격증 수정했다!!");
                } else {
                    certificate.setTrainerNo(trainerNo);
                    result = certificateService.insert(certificate);
                    log.info(";;;;;;;;자격증 이미지insert;;;;;;;;; : " + filesList.toString());
                    log.info("자격증 등록했다");
                }

                if (result > 0) {
                    log.info("자격증 성공했다;;;;;;;;11111111111111");
                } else {
                    log.info(certificate.toString());
                    log.info("자격증 망했다;;;;;;;;;;11111111111");
                }
            }



        int result = trainerService.update(trainer);

        log.debug("Trainer data : {}", trainer);

        if (result > 0) {
            return "redirect:/trainer/info_update?userId=" + trainer.getUserId();
        }
        return "redirect:/trainer/info_update?userId=" + trainer.getUserId() + "&error";
    }


    
    // [은아] - 나는 이거 안 씀
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        // 글 삭제 요청
        // int result = trainerService.delete(no);
        int result = 0;
        // 글 삭제가 되면, 첨부파일도 같이 삭제
        if(result > 0) {
            // 첨부파일 삭제
            Files file = new Files();
            file.setParentTable("board");
            file.setParentNo(no);
            fileService.deleteByParent(file);
            
            return "redirect:/trainer/board/list";
        }
        return "redirect:/trainer/board/update?no=" + no + "&error";
    }
    
}
