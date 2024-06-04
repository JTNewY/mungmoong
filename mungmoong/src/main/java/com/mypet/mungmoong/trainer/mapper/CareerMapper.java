package com.mypet.mungmoong.trainer.mapper;


import java.util.List;

// import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.trainer.dto.Career;

@Mapper     // Mybatis의 매퍼 인터페이스로 지정하는 어노테이션
public interface CareerMapper {

    // 경력 조회 리스트
    public List<Career> select(String userId);

    // 경력 조회
    public Career selectByTrainerNo(int trainerNo) throws Exception;

    // 경력 등록
    public int insert(Career career) throws Exception;
    // 경력 수정
    public int update(Career career) throws Exception;
    
    // 경력 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 경력 데이터 개수 조회

    // public int count(@RequestParam("option") Option option) throws Exception;

    // 경력 목록 - [검색]
    // 매개변수 하나가 들어온다면 xml의 keyword가 text 등이여도 받아올 수 있다.
    // public List<Board> search(@Param("keyword") String keyword) throws Exception;
    // public List<Trainer> search(/* @Param("option") Option option */) throws Exception;


    
}