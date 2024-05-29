package com.mypet.mungmoong.trainer.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.trainer.dto.Trainer;

@Mapper     // Mybatis의 매퍼 인터페이스로 지정하는 어노테이션
public interface TrainerMapper {

    // 게시글 조회
    public Trainer select(String userId);
    // 게시글 등록
    public int insert(Trainer trainer) throws Exception;
    // 게시글 수정
    public int update(Trainer trainer) throws Exception;
    
    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 게시글 데이터 개수 조회
    // public int count(@Param("option") Option option) throws Exception;

    
}