package com.mypet.mungmoong.trainer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mypet.mungmoong.trainer.dto.Board;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.trainer.dto.Option;

@Mapper     // Mybatis의 매퍼 인터페이스로 지정하는 어노테이션
public interface TrainerMapper {

    // 게시글 목록 - [페이징] + [검색]
    // public List<Board> list(Page page) throws Exception;
     public List<Trainer> list(/* @Param("page") Page page
                            ,@Param("option") Option option */) throws Exception;

    // 게시글 조회
    public Trainer select(int no) throws Exception;
    // 게시글 등록
    public int insert(Trainer trainer) throws Exception;
    // 게시글 수정
    public int update(Trainer trainer) throws Exception;
    
    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 게시글 데이터 개수 조회
    public int count(/* @Param("option") Option option */) throws Exception;

    // 게시글 목록 - [검색]
    // 매개변수 하나가 들어온다면 xml의 keyword가 text 등이여도 받아올 수 있다.
    // public List<Board> search(@Param("keyword") String keyword) throws Exception;
    public List<Trainer> search(/* @Param("option") Option option */) throws Exception;

    // 조회 수 증가
    public int view(int no) throws Exception;
    
}