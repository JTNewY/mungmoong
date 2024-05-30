package com.mypet.mungmoong.trainer.service;


import com.mypet.mungmoong.trainer.dto.Trainer;

public interface CareerService {

    // 경력 조회
    public Trainer select(String userId) throws Exception; 
    // 경력 등록
    public int insert(Trainer trainer) throws Exception;
    // 경력 수정
    public int update(Trainer trainer) throws Exception;

    
}