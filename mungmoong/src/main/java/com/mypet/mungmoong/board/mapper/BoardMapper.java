package com.mypet.mungmoong.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;


@Mapper
public interface BoardMapper {
    // 게시글 목록
        // 게시글 목록 - [페이징] + [검색]
    // public List<Board> list(Page page) throws Exception;
    public List<Board> list(@Param("page") Page page
                           ,@Param("option") Option option) throws Exception;
    // 게시글 조회
    public Board select(int no) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 보드 게시글 수정
    public int boardUpdate(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int no) throws Exception;
    // 관리자 삭제
    public int BoardDelete(int no) throws Exception;
    // 관리자 선택 삭제
    public int SelectDelete(String deleteNos) throws Exception;
    // 관리자 보드 삭제
    // public List<Board> checkDelete(int no) throws Exception;
    
    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option option) throws Exception;

    // 게시글 목록 - [검색]
    // public List<Board> search(@Param("keyword") String keyword) throws Exception;
    public List<Board> search(@Param("option") Option option) throws Exception;
    

        
    }
    

