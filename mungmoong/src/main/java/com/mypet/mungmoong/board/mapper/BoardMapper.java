package com.mypet.mungmoong.board.mapper;

import com.mypet.mungmoong.board.dto.Board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    // 게시글 목록
    public List<Board> list() throws Exception;
    // 게시글 조회
    public Board select(int no) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int no) throws Exception;
    // 관리자 삭제
    // public int BoardDelete(int no) throws Exception;
    // 관리자 보드 삭제
    // public List<Board> checkDelete(int no) throws Exception;
    
    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;
    
}
