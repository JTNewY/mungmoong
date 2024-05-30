package com.mypet.mungmoong.trainer.service;


import com.mypet.mungmoong.trainer.dto.Trainer;

public interface TrainerService {

    // 훈련사 조회
    public Trainer select(String userId) throws Exception;  
    // 훈련사 등록
    public int insert(Trainer trainer) throws Exception;
    // 훈련사 수정
    public int update(Trainer trainer) throws Exception;

    
}