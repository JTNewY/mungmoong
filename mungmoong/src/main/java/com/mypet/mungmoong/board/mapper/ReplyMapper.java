package com.mypet.mungmoong.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.board.dto.Reply;


@Mapper
public interface ReplyMapper {
    
    // 댓글 목록
    public List<Reply> list() throws Exception;
    // ⭐댓글 목록
    public List<Reply> listByBoardNo(int boardNo) throws Exception;

    // 댓글 조회
    public Reply select(int no) throws Exception;
    // 댓글 등록
    public int insert(Reply reply) throws Exception;
    // 댓글 수정
    public int update(Reply reply) throws Exception;
    // 댓글 삭제
    public int delete(int no) throws Exception;
    
    // ⭐댓글 종속 삭제
    public int deleteByBoardNo(int boardNo) throws Exception;

    // ⭐최댓값
    public int max() throws Exception;

    // ⭐답글 종속 삭제
    public int deleteByParentNo(int parentNo) throws Exception;

    // 부모 테이블 기준으로 목록 조회
    public List<Reply> listByParent(Reply reply) throws Exception;
}
