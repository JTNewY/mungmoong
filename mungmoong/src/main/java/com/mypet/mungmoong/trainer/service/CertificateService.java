package com.mypet.mungmoong.trainer.service;


import com.mypet.mungmoong.trainer.dto.Trainer;

public interface CertificateService {

    // 자격증 조회
    public Trainer select(String userId) throws Exception; 
    // 자격증 등록
    public int insert(Trainer trainer) throws Exception;
    // 자격증 수정
    public int update(Trainer trainer) throws Exception;

    
}