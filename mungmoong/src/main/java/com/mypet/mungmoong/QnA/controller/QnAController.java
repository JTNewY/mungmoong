package com.mypet.mungmoong.QnA.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.QnA.dto.QnA;
import com.mypet.mungmoong.QnA.service.QnAService;
import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.dto.Reply;
import com.mypet.mungmoong.board.service.ReplyService;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/QnA")
public class QnAController {

  // ⭐데이터 요청과 화면 출력
    // Controller --> Service (데이터 요청)
    // Controller <-- Service (데이터 전달)
    // Controller --> Model   (모델 등록)
    // View <-- Model         (데이터 출력)
    @Autowired
    private QnAService qnaService;      // @Service를 --Impl 에 등록

    
    @Autowired
    private ReplyService replyService;   //댓글
  
    /**
     * 게시글 목록 조회 화면
     * @return
     * @throws Exception 
     */
    @GetMapping("/list")
    public String list(Model model,Page page,Option option ) throws Exception {
        // 데이터 요청
        List<QnA> qnaList = qnaService.list(page, option);
        log.info("문의 리스트 : " + qnaList);
        // 페이징
        log.info("page : " + page);
        // 모델 등록
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("page", page);

           // 동적으로 옵션값을 가져오는 경우
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("전체", 0));
        optionList.add(new Option("제목", 1));
        optionList.add(new Option("내용", 2));
        optionList.add(new Option("제목+내용", 3));
        optionList.add(new Option("작성자", 4));
        model.addAttribute("optionList", optionList);
        // 뷰 페이지 지정
        return "/QnA/list";       // resources/templates/board/list.html
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
    public String read(@RequestParam("no") int no, Model model) throws Exception {
        // 데이터 요청
        QnA qna = qnaService.select(no);
        // 모델 등록
        model.addAttribute("qna", qna);
        // 뷰페이지 지정
        return "/QnA/read";
    }
    
    /**
     * 게시글 등록 화면
     * @return
     */
    @GetMapping("/insert")
    public String insert() {

        return "/QnA/insert";
    }

    /**
     * 게시글 등록 처리
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/insert")
    public String insertPro(QnA qna) throws Exception {
        // 데이터 요청
        int result = qnaService.insert(qna);
        // 리다이렉트
        // ⭕ 데이터 처리 성공
        if( result > 0 ) {
            return "redirect:/QnA/list";
        }
        // ❌ 데이터 처리 실패
        return "redirect:/QnA/insert?error";  
    }
    
    /**
     * 게시글 수정 화면
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        QnA qna = qnaService.select(no);
        model.addAttribute("qna", qna);
        return "/QnA/update";
    }

    /**
     * 게시글 수정 처리
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro(QnA qna) throws Exception {
        int result = qnaService.update(qna);

        if( result > 0 ) {
            return "redirect:/QnA/list";
        }
        int qnaNo = qna.getNo();
        return "redirect:/QnA/update?qnaNo="+ qnaNo + "&error";
    }
    
    /**
     * 게시글 삭제 처리
     * @param no
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        int result = qnaService.delete(no);
        if( result > 0 ) {
            return "redirect:/QnA/list";
        }
        return "redirect:/QnA/update?no=" + no + "&error";
    }
    

        /**
     * 관리자 댓글 목록
     * @param reply
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @GetMapping("/reply")
    public String replylist(Reply reply
                                             ,Model model
                                             ,HttpSession session
                                            ) throws Exception {
        log.info(":::::::::: 댓글목록 ::::::::::");
        List<Reply> replyList = replyService.listByParent(reply); 
        log.info(": " + replyList);
        
        model.addAttribute("replyList", replyList);
        return "/reply/list";       
    }


    @PostMapping("/reply")
    public ResponseEntity<String> replyInsert(@RequestBody Reply reply
                                             , HttpSession session
                                            ) throws Exception {
        // int result = replyService.insert(reply);
        log.info(":::::::::: 댓글입력 ::::::::::");
        log.info(reply.toString());
        reply.setParentTable("qna");
        int result = replyService.insert(reply);
        if (result > 0) {
            return ResponseEntity.ok("SUCCESS");
        } else {
            return ResponseEntity.status(500).body("FAILURE");
        }
        // return null;       
    }
}
