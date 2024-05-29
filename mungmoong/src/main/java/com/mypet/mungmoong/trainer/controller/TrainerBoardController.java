package com.mypet.mungmoong.trainer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.FileService;
import com.mypet.mungmoong.trainer.service.TrainerService;

import lombok.extern.slf4j.Slf4j;


/**
 *  /board 경로로 요청 왔을 때 처리
 * [GET]  - /board/list   : 게시글 목록 화면
 * [GET]  - /board/read   : 게시글 조회 화면
 * [GET]  - /board/insert : 게시글 등록 화면
 * [POST] - /board/insert : 게시글 등록 처리
 * [GET]  - /board/update : 게시글 수정 화면
 * [POST] - /board/update : 게시글 수정 처리 
 * [POST] - /board/delete : 게시글 삭제 처리
 */
@Slf4j                                  // 로그 어노테이션
@Controller                             // 컨트롤러 스프링 빈으로 등록 -> 여러가지 매핑 사용 가능
@RequestMapping("/trainer")       // 상위 경로를 먼저 지정해주고 싶을 때
                                        // 클래스 레벨 요청 경로 매핑 - /board~ 경로의 요청을 처리
public class TrainerBoardController {
    
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

    /**
     * 게시글 목록 조회 화면
     * @return
     * @throws Exception 
     */
    @GetMapping("/list")
             // 아무것도 넣지 않았을 때, page 기본 생성 - (1, 10, 10, ?)
    public String list(Model model, Page page 
             // 아무것도 넣지 않았을 때, defaultValue에 빈 문자열 -> /board/list
                   // , @RequestParam(value = "keyword", defaultValue = "") String keyword
                      , Option option
                    ) throws Exception {
        // 데이터 요청
        List<Trainer> trainerList = trainerService.list(page);         // [페이징]
        // List<Board> trainerList = TrainerService.search(keyword);    // [검색]
        // List<Board> trainerList = TrainerService.search(option);     // [검색]
        // List<Board> trainerList = TrainerService.list(page, option);    // [페이징] + [검색]

        // 페이징
        log.info("page : " + page);
        // 검색
        // log.info("keyword : " + keyword);
        log.info("keyword : " + option);

        // 모델 등록
        model.addAttribute("trainerList", trainerList);
        model.addAttribute("page", page);

        // 동적으로 옵션 값을 가져오는 경우
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("전체", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("제목+내용", 3));
        optionList.add(new Option("작성자", 4));
        model.addAttribute("optionList", optionList);

        // 뷰 페이지 지정
        return "/trainer/board/list";       // resources/templates/board/list.html
    }
    
    /**
     * 게시글 조회 화면 
     * - /board/read?no=❔
     * @param no
     * @return
     * @throws Exception 
     */
    // @RequestParam("파라미터명") : 스프링 부트 3.2 버전 이상에서는 생략하면 매핑 불가능
    @GetMapping("/read")
    public String read(@RequestParam("no") int no
                      , Model model
                      , Files file) throws Exception {
        // 데이터 요청
        Trainer trainer = trainerService.select(no);

        // 조회 수 증가
        int views = trainerService.view(no);

        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(no);
        List<Files> fileList = fileService.listByParent(file);

        // 모델 등록
        model.addAttribute("trainer", trainer);
        model.addAttribute("fileList", fileList);
        
        // 뷰페이지 지정
        return "/trainer/board/read";
    }
    
    /**
     * 게시글 등록 화면
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
     * 게시글 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model, Files file) throws Exception {

        // 데이터 요청
        Trainer trainer = trainerService.select(no);

        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(no);
        List<Files> fileList = fileService.listByParent(file);

        // 모델 등록
        model.addAttribute("trainer", trainer);
        model.addAttribute("fileList", fileList);

        return "/trainer/board/update";
    }

    /**
     * 게시글 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro(Trainer trainer) throws Exception {
        int result = trainerService.update(trainer);
        if(result > 0) {
            return "redirect:/trainer/board/list";
        }
        String no = trainer.getNo();
        return "redirect:/trainer/board/update?no=" + no + "&error";
    }
    

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
