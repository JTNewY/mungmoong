package com.mypet.mungmoong.trainer.service;


import java.util.List;

import com.mypet.mungmoong.trainer.dto.Career;

public interface CareerService {

    // 경력 조회 리스트
    public List<Career> select(String userId) throws Exception;

    // 경력 조회
    public Career selectByTrainerNo(int trainerNo) throws Exception;

    // 경력 등록
    public int insert(Career career) throws Exception;
    // 경력 수정
    public int update(Career career) throws Exception;

    
}