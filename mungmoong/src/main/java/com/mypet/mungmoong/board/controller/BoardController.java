package com.mypet.mungmoong.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.service.BoardService;
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
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public String list(Model model) throws Exception {
        // 데이터 요청
        List<Board> boardList = boardService.list();
        // 모델 등록
        model.addAttribute("boardList", boardList);
        // 뷰 페이지 지정
        return "/board/list"; // resources/templates/board/list.html
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
    public String read(@RequestParam("boardNo") int no, Model model) throws Exception {
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
    public String insert(@SessionAttribute("user") Users user) {
        String userId = user.getUserId();
        
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
    public String insertPro(@SessionAttribute("user") Users user, Board board) throws Exception {
        String userId = user.getUserId();
        board.setUserId(userId);

        log.info(board.toString());
        // 데이터 요청
        int result = boardService.insert(board);
        // 리다이렉트
        // ⭕ 데이터 처리 성공
        if (result > 0) {
            return "redirect:/board/list";
        }
        // ❌ 데이터 처리 실패
        return "redirect:/board/insert?error";
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
    public String update(@RequestParam("boardNo") int no, Model model) throws Exception {
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
        int no = board.getBoardNo();
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
    public String delete(@RequestParam("boardNo") int no) throws Exception {
        int result = boardService.delete(no);
        if (result > 0) {
            return "redirect:/board/list";
        }
        return "redirect:/board/update?no=" + no + "&error";
    }

}
