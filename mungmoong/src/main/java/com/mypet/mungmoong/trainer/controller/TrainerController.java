package com.mypet.mungmoong.trainer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.main.model.Event;
import com.mypet.mungmoong.orders.dto.Orders;
import com.mypet.mungmoong.orders.service.OrdersService;
import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.service.PetService;
import com.mypet.mungmoong.trainer.dto.Career;
import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Schedule;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.CareerService;
import com.mypet.mungmoong.trainer.service.CertificateService;
import com.mypet.mungmoong.trainer.service.FileService;
import com.mypet.mungmoong.trainer.service.ScheduleService;
import com.mypet.mungmoong.trainer.service.TrainerService;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

/**
 * [GET] - /trainer/list : 훈련사 목록 화면
 * [GET] - /trainer/read : 훈련사 조회 화면
 * [GET] - /trainer/insert : 훈련사 등록 화면
 * [POST] - /trainer/insert : 훈련사 등록 처리
 * [GET] - /trainer/update : 훈련사 수정 화면
 * [POST] - /trainer/update : 훈련사 수정 처리
 * [POST] - /trainer/delete : 훈련사 삭제 처리
 */
@Slf4j
@Controller
@RequestMapping("/trainer")
public class TrainerController {

    private Logger logger = LoggerFactory.getLogger(TrainerController.class);

    @GetMapping("/{page}")
    public String test(@PathVariable("page") String page) {
        return "/trainer/" + page;
    }

    // ⭐ 데이터 요청과 화면 출력
    // Controller --> Service (데이터 요청)
    // Controller <-- Service (데이터 전달)
    // Controller --> Model (모델 등록)
    // View <-- Model (데이터 출력)
    @Autowired
    private TrainerService trainerService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private PetService petService;

    @Autowired
    private UsersService userService;

    /**
     * Orders 목록
     * 
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/orders")
    public String ordersList(Model model, HttpSession session) throws Exception {
        log.info("[GET] - /trainer/orders");
        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if (trainerNo == null) {
            log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
            // 트레이너 번호가 없을 경우 에러 처리
            model.addAttribute("error", "트레이너 번호를 세션에서 찾을 수 없습니다.");
            return "/trainer/error";
        }
        // 데이터 요청
        log.info("trainerNo : " + trainerNo);
        List<Orders> ordersList = ordersService.listByTrainer(trainerNo);

        // 모델 등록
        model.addAttribute("ordersList", ordersList);

        // 뷰 페이지 지정
        return "/trainer/orders";
    }

    /**
     * 입금 내역 목록
     * 
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/deposit")
    public String deposit(Model model, HttpSession session) throws Exception {
        log.info("[GET] - /trainer/orders");
        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if (trainerNo == null) {
            log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
            // 트레이너 번호가 없을 경우 에러 처리
            model.addAttribute("error", "트레이너 번호를 세션에서 찾을 수 없습니다.");
            return "/trainer/error";
        }
        // 데이터 요청
        log.info("trainerNo : " + trainerNo);
        List<Orders> ordersList = ordersService.listByTrainer(trainerNo);

        // 총 금액 계산
        int totalAmount = ordersList.stream().mapToInt(Orders::getPrice).sum();

        // 승인된 주문 필터링 및 총 금액 계산
        List<Orders> approvedOrdersList = ordersList.stream()
                .filter(order -> "approval".equals(order.getStatus()))
                .collect(Collectors.toList());

        int totalApprovedAmount = approvedOrdersList.stream().mapToInt(Orders::getPrice).sum();

        // 모델 등록
        model.addAttribute("ordersList", ordersList);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("approvedOrdersList", approvedOrdersList);
        model.addAttribute("totalApprovedAmount", totalApprovedAmount);

        // 뷰 페이지 지정
        return "/trainer/deposit";
    }

    /**
     * Meaning 수정 작업
     * 
     * @param orderId
     * @param meaning
     * @return
     * @throws Exception
     */
    @PostMapping("/orders")
    public String updateOrderMeaning(@RequestParam("orderNo") int orderNo, @RequestParam("meaning") int meaning)
            throws Exception {
        ordersService.updateMeaning(orderNo, meaning);
        return "redirect:/trainer/orders";
    }

    /**
     * Orders 조회
     * 
     * @param no
     * @param model
     * @param file
     * @return
     * @throws Exception
     */
    @GetMapping("/orders_details")
    public String ordersDetails(@RequestParam("no") int no,
            Model model,
            Files file) throws Exception {
        Orders orders = ordersService.select(no);
        int petNo = orders.getPetNo();
        log.info("petNo :  " + petNo);
        Pet pet = petService.findPetById(petNo);
        log.info(":::::  pet  ::::::" + pet.toString());
        log.info(":::: orders :::::" + orders.toString());

        // 파일 요청

        // 모델 등록
        model.addAttribute("orders", orders);
        model.addAttribute("pet", pet);

        // 뷰페이지 지정
        return "/trainer/orders_details";
    }

    /**
     * 훈련사 정보 조회 (경력, 소개, 자격증)
     * 
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

                String userId = (String) session.getAttribute("userId");
                Users updatedUser = userService.select(userId);
                session.setAttribute("user", updatedUser);
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
    public String update(@RequestParam("userId") String userId, Model model, Files file, HttpSession session)
            throws Exception {
        Trainer trainer = trainerService.select(userId);
        List<Career> careerList = careerService.select(userId); // select -> listByUserId
        List<Certificate> certificateList = certificateService.listByUserId(userId);
        List<Files> fileList = fileService.listByParent(file);
        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if (trainerNo == null) {
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
     * 
     * @param trainer
     * @param session
     * @param model
     * @throws Exception
     */
    @PostMapping("/info_update")
    public String updatePro(Trainer trainer, @RequestParam("files") List<MultipartFile> files, HttpSession session) throws Exception {
        log.info(":::::::::::::::::: 훈련사 정보 수정 :::::::::::::::::::");
        log.info("trainser : " + trainer.toString());
    
        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if (trainerNo == null) {
            log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
            return "redirect:/trainer/info_update?userId=" + trainer.getUserId() + "&error=session";
        }
        log.info("세션에서 가져온 트레이너 번호 : " + trainerNo);
    
        List<Career> careerList = trainer.toCareerList();
        for (Career career : careerList) {
            career.setTrainerNo(trainerNo);
            int result = (career.getNo() > 0) ? careerService.update(career) : careerService.insert(career);
            log.info(result > 0 ? "성공!" : "실패..");
        }
    
        List<Certificate> certificateList = trainer.toCertificateList();
        log.info("certificateList : " + certificateList);
        log.info("업로드 파일 목록 - files : " + files);
    
        for (int i = 0; i < certificateList.size(); i++) {
            Certificate certificate = certificateList.get(i);
            certificate.setTrainerNo(trainerNo);
    
            int result = (certificate.getNo() > 0) ? certificateService.update(certificate) : certificateService.insert(certificate);
            if (result > 0) {
                log.info("자격증 성공");
            } else {
                log.info("자격증 실패");
            }
    
            if (i < files.size()) {
                MultipartFile file = files.get(i);
                if (!file.isEmpty()) {
                    Files fileEntity = new Files();
                    fileEntity.setFile(file);
                    fileEntity.setParentTable("certificate");
                    fileEntity.setParentNo(certificate.getNo());  // 이 시점에서 certificate.getNo()는 올바른 값이어야 함
                    fileService.upload(fileEntity);
                    certificate.setImgFile(fileEntity); // Files 객체를 자격증 객체에 설정
                    certificate.insertImg();
                }
            }
        }
    
        int result = trainerService.update(trainer);
        log.debug("Trainer data : {}", trainer);
    
        return "redirect:/trainer/info_update?userId=" + trainer.getUserId() + (result > 0 ? "" : "&error");
    }
    
    

    // @PostMapping("/info_update")
    // public String updatePro(Trainer trainer, HttpSession session) throws
    // Exception {
    // log.info(":::::::::::::::::: 훈련사 정보 수정 :::::::::::::::::::");
    // log.info("trainser : " + trainer.toString());
    // List<Career> careerList = trainer.toCareerList();
    // List<Certificate> certificateList = trainer.toCertificateList();
    // List<Files> filesList = fileService.list();
    // List<MultipartFile> files = trainer.getFiles();

    // log.info("--------------------------------");
    // log.info(careerList.toString());
    // log.info("트레이너 번호가 뭘까요 : " + trainer.getNo());

    // Integer trainerNo = (Integer) session.getAttribute("trainerNo");
    // if (trainerNo == null) {
    // log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
    // return "redirect:/trainer/info_update?userId=" + trainer.getUserId() +
    // "&error=session";
    // }
    // log.info("세션에서 가져온 트레이너 번호 : " + trainerNo);

    // for (Career career : careerList) {
    // career.setTrainerNo(trainerNo);
    // log.info("trainerNo : " + trainerNo);

    // int result = 0;
    // if (career.getNo() > 0) {
    // result = careerService.update(career);
    // log.info("수정 완료!");
    // } else {
    // career.setTrainerNo(trainerNo);
    // result = careerService.insert(career);
    // log.info("등록 완료!");
    // }

    // if (result > 0) {
    // log.info("성공!");
    // } else {
    // log.info(career.toString());
    // log.info("실패..");
    // }
    // }

    // log.info(":::::::::::::::::::::: certificateList ::::::::::::::::::::::::");
    // log.info("certificateList : " + certificateList);
    // log.info(":::::::::::::::::::::: 업로드 파일 목록 - files
    // ::::::::::::::::::::::::");
    // log.info("files : " + files);

    // for (int i = 0; i < certificateList.size(); i++) {
    // Certificate certificate = certificateList.get(i);
    // certificate.setTrainerNo(trainerNo);
    // log.info("trainerNo : " + trainerNo);

    // // 자격증 객체에 이미지 파일 담음
    // certificate.setInsertFile(files.get(i));
    // certificate.insertImg();

    // int result = 0;
    // if (certificate.getNo() > 0) {
    // result = certificateService.update(certificate);
    // log.info(";;;;;;;;자격증 이미지update;;;;;;;;; : " + filesList.toString());
    // log.info("자격증 수정");
    // } else {
    // certificate.setTrainerNo(trainerNo);
    // result = certificateService.insert(certificate);
    // log.info(";;;;;;;;자격증 이미지insert;;;;;;;;; : " + filesList.toString());
    // log.info("자격증 등록");
    // }

    // if (result > 0) {
    // log.info("자격증 성공");
    // } else {
    // log.info(certificate.toString());
    // log.info("자격증 실패");
    // }
    // }

    // int result = trainerService.update(trainer);

    // log.debug("Trainer data : {}",trainer);

    // if(result>0)
    // {
    // return "redirect:/trainer/info_update?userId=" + trainer.getUserId();
    // }return"redirect:/trainer/info_update?userId="+trainer.getUserId()+"&error";
    // }

    // [은아] - 나는 이거 안 씀
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        // 글 삭제 요청
        // int result = trainerService.delete(no);
        int result = 0;
        // 글 삭제가 되면, 첨부파일도 같이 삭제
        if (result > 0) {
            // 첨부파일 삭제
            Files file = new Files();
            file.setParentTable("board");
            file.setParentNo(no);
            fileService.deleteByParent(file);

            return "redirect:/trainer/board/list";
        }
        return "redirect:/trainer/board/update?no=" + no + "&error";
    }

    // 스케쥴 👩‍🏫(full calendar 샘플)
    @GetMapping("/schedule")
    public String scheduleCalendar(HttpSession session, Model model) throws Exception {
        Integer trainerNo = (Integer) session.getAttribute("trainerNo");
        if (trainerNo == null) {
            log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
            // 트레이너 번호가 없을 경우 에러 처리
            model.addAttribute("error", "트레이너 번호를 세션에서 찾을 수 없습니다.");
            return "/trainer/error";
        }
        List<Schedule> scheduleList = scheduleService.select(trainerNo);
        model.addAttribute("trainerNo", trainerNo);
        model.addAttribute("scheduleList", scheduleList);
        return "/trainer/schedule";
    }

    // 스케쥴 등록
    @PostMapping("/schedule")
    public String saveSchedule(@ModelAttribute Schedule schedule,
            @RequestParam("title") String title,
            @RequestParam("scheduleDate") Date scheduleDate,
            HttpSession session, Model model) {
        try {
            Integer trainerNo = (Integer) session.getAttribute("trainerNo");
            Users loginUser = (Users) session.getAttribute("user");
            String userId = loginUser.getUserId();
            log.info("저장된 아이디 : " + userId);
            if (trainerNo == null) {
                log.error("트레이너 번호를 세션에서 찾을 수 없습니다.");
                // 트레이너 번호가 없을 경우 에러 처리
                model.addAttribute("error", "트레이너 번호를 세션에서 찾을 수 없습니다.");
                return "/trainer/error";
            }
            schedule.setTrainerNo(trainerNo);
            schedule.setTitle(title);
            schedule.setUserId(userId);
            schedule.setScheduleDate(scheduleDate);
            int result = scheduleService.insert(schedule);

            if (result > 0) {
                log.info("스케쥴 등록이 완료되었습니다╰(*°▽°*)╯");
                return "redirect:/trainer/schedule";
            }
        } catch (Exception e) {
            log.error("Error occurred while processing trainer data", e);
            model.addAttribute("errorMessage", "Error occurred while processing trainer data: " + e.getMessage());
        }
        return "redirect:/trainer/schedule?error";
    }

    /**
     * 캘린더 데이터
     * - 훈련사 번호를 받아오면 해당 훈련사의 일정을
     * JSON 데이터로 응답함
     * 
     * @param trainerNo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/schedule/event")
    public List<Event> trainerScheduleEvent(@RequestParam("trainerNo") int trainerNo, Event event) throws Exception {
        List<Schedule> scheduleList = scheduleService.select(trainerNo);
        // log.info("스케쥴 확인할 훈련사 번호 : " + trainerNo);
        // log.info("::::::::::::::: 스케쥴 ::::::::::::::::::");
        // log.info(scheduleList.toString());
        List<Event> eventList = new ArrayList<>();
        // 풀캘린더에 맞는 변수로 변환
        for (Schedule schedule : scheduleList) {
            int no = schedule.getNo();
            String title = schedule.getTitle();
            Date date = schedule.getScheduleDate();
            String description = schedule.getContent();
            eventList.add(new Event(no, title, description, date));
        }
        return eventList;
    }

    /**
     * 일정 삭제
     * 
     * @param no
     * @return
     * @throws Exception
     */
    @DeleteMapping("/schedule/event/{no}")
    public ResponseEntity<String> deleteTrainerScheduleEvent(@PathVariable("no") int no) throws Exception {
        log.info("스케쥴 번호 - no " + no);
        int result = scheduleService.deleteByNo(no);
        if (result > 0) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FAIL", HttpStatus.OK);
        }
    }

}
