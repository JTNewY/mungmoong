package com.mypet.mungmoong.trainer.service;


import com.mypet.mungmoong.trainer.dto.Trainer;

public interface TrainerService {

    // 게시글 조회
    public Trainer select(int no) throws Exception;  // 이 메소드를 호출하는 곳으로 예외 전가
    // 게시글 등록
    public int insert(Trainer trainer) throws Exception;
    // 게시글 수정
    public int update(Trainer trainer) throws Exception;

    
}