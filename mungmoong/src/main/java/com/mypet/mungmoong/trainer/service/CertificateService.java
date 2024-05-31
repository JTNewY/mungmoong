package com.mypet.mungmoong.trainer.service;


import java.util.List;

import com.mypet.mungmoong.trainer.dto.Certificate;

public interface CertificateService {

    // 자격증 조회
    public List<Certificate> select(int trainerNo) throws Exception; 
    // 자격증 등록
    public int insert(Certificate certificate) throws Exception;
    // 자격증 수정
    public int update(Certificate certificate) throws Exception;

    
}