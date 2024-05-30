package com.mypet.mungmoong.trainer.service;


import java.util.List;

import com.mypet.mungmoong.trainer.dto.Reply;


public interface ReplyService {
    // 댓글 목록
    public List<Reply> list() throws Exception;

    // 댓글 목록
    public List<Reply> listByBoardNo(int boardNo) throws Exception;

    // 댓글 조회
    public Reply select(int no) throws Exception;

    // 댓글 등록
    public int insert(Reply reply) throws Exception;

    // 댓글 수정
    public int update(Reply reply) throws Exception;

    // 댓글 삭제
    public int delete(int no) throws Exception;

    // 🍮 댓글 종속 삭제
    public int deleteByBoardNo(int boardNo) throws Exception;

    // 🧇 최댓값
    public int max() throws Exception;

     // 답글종속 삭제
     public int deleteByParentNo(int parentNo) throws Exception;
}