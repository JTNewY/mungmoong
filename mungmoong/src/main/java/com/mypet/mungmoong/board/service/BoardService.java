package com.mypet.mungmoong.board.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

public interface BoardService {
    // 게시글 목록
    public List<Board> list(@Param("page") Page page
                           ,@Param("option") Option option) throws Exception;
    // 게시글 조회

    public Board select(int no) throws Exception;

    // 게시글 등록
    public int insert(Board board) throws Exception;

    // 게시글 수정
    public int update(Board board) throws Exception;

    // 게시글 삭제
    public int delete(int no) throws Exception;

    // 관리자 삭제
    public int BoardDelete(int no) throws Exception;

    // 관리자 선택 삭제
    public int SelectDelete(String[] SelectNoList) throws Exception;

    // 관리자 보드 삭제
    // public List<Board> checkDelete(int no) throws Exception;
    // 게시글 목록 - [검색]
    // public List<Board> search(String keyword) throws Exception;
    public List<Board> search(Option option) throws Exception;


}
