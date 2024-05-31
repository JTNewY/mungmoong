package com.mypet.mungmoong.trainer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.mapper.CertificateMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service        // 서비스 역할의 스프링 빈 등록
public class CertificateServiceImpl implements CertificateService {
    
    @Autowired
    private CertificateMapper certificateMapper;
    
    @Autowired
    private FileService fileService;

    @Override
    public Certificate select(String userId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'select'");
    }

    @Override
    public int insert(Certificate certificate) throws Exception {
        int result = certificateMapper.insert(certificate);

        return result;
    }

    @Override
    public int update(Certificate certificate) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }



}
