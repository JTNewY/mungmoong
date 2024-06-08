package com.mypet.mungmoong.trainer.service;

import java.util.List;

import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.trainer.dto.Trainer;

public interface TrainerService {

    // 훈련사 조회
    public Trainer select(String userId) throws Exception;

    // 훈련사 등록
    public int insert(Trainer trainer) throws Exception;

    // 훈련사 수정
    public int update(Trainer trainer) throws Exception;

    // 트레이너 리스트 호출
    public List<Trainer> trainerList(Page page, Option option) throws Exception;
    
    // 관리자 트레이너 리스트
    public List<Trainer> adminTrainerList(Page page, Option option) throws Exception;

    // 훈련사 번호 조회
    public int selectTrainerNo() throws Exception;

    // 훈련사 번호로 조회
    public Trainer selectByNo(int trainerNo);

}