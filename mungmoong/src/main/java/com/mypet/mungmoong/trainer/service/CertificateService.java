package com.mypet.mungmoong.trainer.service;


import java.util.List;

import com.mypet.mungmoong.trainer.dto.Certificate;

public interface CertificateService {

    // 자격증 조회
    public List<Certificate> listByUserId(String userId) throws Exception; 
    // 자격증 등록
    public int insert(Certificate certificate) throws Exception;
    // 자격증 수정
    public int update(Certificate certificate) throws Exception;
    // 자격증 삭제 - no 와, ImgFile 안에 no(fileNo) 를 가져와야함
    public int delete(Certificate certificate) throws Exception;
    
}