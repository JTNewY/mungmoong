package com.mypet.mungmoong.trainermain.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.service.BoardService;
import com.mypet.mungmoong.trainer.dto.Career;
import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.service.CareerService;
import com.mypet.mungmoong.trainer.service.CertificateService;
import com.mypet.mungmoong.trainer.service.FileService;
import com.mypet.mungmoong.trainer.service.TrainerService;

import lombok.extern.slf4j.Slf4j;

/**
 *  /board 경로로 요청 왔을 때 처리
 *  [GET]   - /board/list   : 게시글 목록 화면
 *  [GET]   - /board/read   : 게시글 조회 화면
 *  [GET]   - /board/insert : 게시글 등록 화면
 *  [POST]  - /board/insert : 게시글 등록 처리
 *  [GET]   - /board/update : 게시글 수정 화면
 *  [POST]  - /board/update : 게시글 수정 처리
 *  [POST]  - /board/delete : 게시글 삭제 처리
 */
@Slf4j                      // 로그 어노테이션
@Controller                 // 컨트롤러 스프링 빈으로 등록
@RequestMapping("/trainermain")   // 클레스 레벨 요청 경로 매핑 
                            // - /board/~ 경로의 요청은 이 컨트롤러에서 처리
public class TrainerMainController {
    
    // ⭐데이터 요청과 화면 출력
    // Controller --> Service (데이터 요청)
    // Controller <-- Service (데이터 전달)
    // Controller --> Model   (모델 등록)
    // View <-- Model         (데이터 출력)
    @Autowired                              // 의존성 자동 주입
    private BoardService boardService;      // @Service를 --Impl 에 등록

    @Autowired
    private FileService fileService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    CareerService careerService;

    @Autowired
    CertificateService certificateService;

    /**
     * 게시글 목록 조회 화면
     * @return
     * @throws Exception 
     */
    @GetMapping("/list")
    public String list(Model model
                      , Page page
                    //   , @RequestParam("userId") String userId
                      , Option option
                    ) throws Exception {
        // 데이터 요청
        // List<Board> boardList = boardService.list(page);         //[페이징]
        // List<Board> boardList = boardService.search(keyword);    //[검색]
        // List<Board> boardList = boardService.search(option);     //[검색]
        List<Board> boardList = boardService.list();    //[페이징]+[검색]
        List<Trainer> trainerList = trainerService.trainerList();
        // List<Career> careers = careerService.select(userId);
        // List<Certificate> certificates = certificateService.listByUserId(userId);

        // 모델 등록
        // model.addAttribute(userId, certificates);
        model.addAttribute("trainerList", trainerList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);
        model.addAttribute("option", option);

        // 동적으로 옵션값을 가져오는 경우
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("전체", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("제목+내용", 3));
        optionList.add(new Option("작성자", 4));
        model.addAttribute("optionList", optionList);

        // 뷰 페이지 지정
        return "/trainermain/list";       // resources/templates/board/list.html
    }
    
    /**
     * 게시글 조회 화면
     * - /board/read?no=💎
     * @param no
     * @return
     * @throws Exception 
     */
    // @RequestParam("파라미터명") 
    // - 스프링 부트 3.2버전 이하, 생략해도 자동 매핑된다.
    // - 스프링 부트 3.2버전 이상, 필수로 명시해야 매핑된다.
    @GetMapping("/read")
    public String read(@RequestParam("boardId") int no
                      , Model model
                      , Files file) throws Exception {
        // 데이터 요청
        Board board = boardService.select(no);


        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(no);
        List<Files> fileList = fileService.listByParent(file);

        // 모델 등록
        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);
        
        // 뷰페이지 지정
        return "/trainermain/read";
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
    public String insertPro(Board board) throws Exception {

        log.info(board.toString());

        // 데이터 요청
        int result = boardService.insert(board);
        // 리다이렉트
        // ⭕ 데이터 처리 성공
        if( result > 0 ) {
            return "redirect:/trainermain/list";
        }
        // ❌ 데이터 처리 실패
        return "redirect:/trainermain/insert?error";  
    }
    
    /**
     * 게시글 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/update")
    public String update(@RequestParam("boardId") int no
                        , Model model
                        , Files file) throws Exception {

        // 데이터 요청
        Board board = boardService.select(no);

        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(no);
        List<Files> fileList = fileService.listByParent(file);

        // 모델 등록
        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);
       
        return "/trainermain/update";
    }

    /**
     * 게시글 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {
        int result = boardService.update(board);

        if( result > 0 ) {
            return "redirect:/board/list";
        }
        int no = board.getBoardNo();
        return "redirect:/trainermain/update?no="+ no + "&error";
    }
    
    /**
     * 게시글 삭제 처리
     * @param no
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("boardId") int no) throws Exception {
        int result = boardService.delete(no);
        if( result > 0 ) {

            // 첨부파일 삭제
            Files file = new Files();
            file.setParentTable("board");
            file.setParentNo(no);
            fileService.deleteByParent(file);

            return "redirect:/trainermain/list";
        }
        return "redirect:/trainermain/update?no=" + no + "&error";
    }
    
    
}