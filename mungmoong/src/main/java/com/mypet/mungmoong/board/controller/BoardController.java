package com.mypet.mungmoong.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.service.BoardService;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    // ⭐데이터 요청과 화면 출력
    // Controller --> Service (데이터 요청)
    // Controller <-- Service (데이터 전달)
    // Controller --> Model (모델 등록)
    // View <-- Model (데이터 출력)
    @Autowired // 의존성 자동 주입
    private BoardService boardService; // @Service를 --Impl 에 등록

    @Autowired
    private UsersService userService;


    /**
     * 게시글 목록 조회 화면
     * 
     * @throws Exception
     */
    @GetMapping("/list")
    public void list(Option option ,Page page ,Model model) throws Exception {
        // 데이터 요청
      //  List<Board> boardList = boardService.list(page);         //[페이징]
        // List<Board> boardList = boardService.search(keyword);    //[검색]
        // List<Board> boardList = boardService.search(option);     //[검색]
       List<Board> boardList = boardService.list(page, option);    //[페이징]+[검색]


        // 페이징
        log.info("page : " + page);
        // 검색
        // log.info("keyword : " + keyword);
       // log.info("option : " + option);

        // 모델 등록
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);
       //  model.addAttribute("option", option);

        // 동적으로 옵션값을 가져오는 경우
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("전체", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("제목+내용", 3));
        optionList.add(new Option("작성자", 4));
        model.addAttribute("optionList", optionList);
    }
    




    /**
     * 게시글 조회 화면
     * - /board/read?no=💎
     * 
     * @param no
     * @return
     * @throws Exception
     */
    // @RequestParam("파라미터명")
    // - 스프링 부트 3.2버전 이하, 생략해도 자동 매핑된다.
    // - 스프링 부트 3.2버전 이상, 필수로 명시해야 매핑된다.
    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model) throws Exception {
        // 데이터 요청
        Board board = boardService.select(no);
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰페이지 지정
        return "/board/read";
    }

    /**
     * 게시글 등록 화면
     * 
     * @return
     */
    @GetMapping("/insert")
    public String insert(HttpSession session, Users user) {
        Users userId = (Users) session.getAttribute("user");
        
        
        log.info("저장된 아이디 : " + userId);

        return "/board/insert";
    }

    /**
     * 게시글 등록 처리
     * 
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String insertPro(Board board, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            board.setUserId(user.getUserId());
        } else {
            board.setUserId("person");
        }
        boardService.insert(board);
        return "redirect:/board/list";
    }

    /**
     * 게시글 수정 화면
     * 
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/update";
    }

    /**
     * 게시글 수정 처리
     * 
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {
        int result = boardService.update(board);

        if (result > 0) {
            return "redirect:/board/list";
        }
        int no = board.getNo();
        return "redirect:/board/update?no=" + no + "&error";
    }

    /**
     * 게시글 삭제 처리
     * 
     * @param no
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        int result = boardService.delete(no);
        if (result > 0) {
            return "redirect:/board/list";
        }
        return "redirect:/board/update?no=" + no + "&error";
    }




    

}
