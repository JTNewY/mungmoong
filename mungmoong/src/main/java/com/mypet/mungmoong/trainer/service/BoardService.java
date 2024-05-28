package com.mypet.mungmoong.trainer.service;

import java.util.List;

import com.mypet.mungmoong.trainer.dto.Board;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

public interface BoardService {

    // 게시글 목록
    // public List<Board> list(Page page) throws Exception;     
    public List<Board> list(Page page, Option option) throws Exception;     
    // 게시글 조회
    public Board select(int no) throws Exception;  // 이 메소드를 호출하는 곳으로 예외 전가
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제 
    public int delete(int no) throws Exception;

    // 게시글 목록 - [검색]
    // public List<Board> search(String keyword) throws Exception;
    public List<Board> search(Option option) throws Exception;

    // 조회 수 증가
    public int view(int no) throws Exception;

    
}