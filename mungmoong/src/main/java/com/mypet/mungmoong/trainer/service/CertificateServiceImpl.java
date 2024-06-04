package com.mypet.mungmoong.trainer.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.mapper.CertificateMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service        // 서비스 역할의 스프링 빈 등록
public class CertificateServiceImpl implements CertificateService {
    
    @Autowired
    private CertificateMapper certificateMapper;

    @Autowired
    private FileService fileService;

    // @Override
    // public List<Certificate> listByUserId(String userId) throws Exception {
    //     List<Certificate> certificateList = certificateMapper.listByUserId(userId);

    //     for (Certificate certificate : certificateList) {
    //         int no = certificate.getNo();
    //         Files file = new Files();
    //         file.setParentTable("certificate");
    //         file.setParentNo(no);
    //         Files imgFile = fileService.listByParent(file).get(0);
    //         log.info("자격증 이미지 : " + imgFile);
    //         certificate.setImgFile(imgFile);
    //     }
    //     return certificateList;
    // }

    @Override
    public List<Certificate> listByUserId(String userId) throws Exception {
        List<Certificate> certificateList = certificateMapper.listByUserId(userId);
    
        for (Certificate certificate : certificateList) {
            int no = certificate.getNo();
            Files file = new Files();
            file.setParentTable("certificate");
            file.setParentNo(no);
    
            List<Files> files = fileService.listByParent(file);
    
            if (files.isEmpty()) {
                log.warn("No files found for certificate no: " + no);
                certificate.setImgFile(null); // 파일이 없으면 null로 설정
                continue; // 빈 파일 리스트인 경우 다음 루프로 넘어감
            }
    
            Files imgFile = files.get(0);
            log.info("자격증 이미지 : " + imgFile);
            certificate.setImgFile(imgFile);
        }
        return certificateList;
    }

    @Override
    public int insert(Certificate certificate) throws Exception {
        int result = certificateMapper.insert(certificate);

        return result;

    }

    @Override
    public int update(Certificate certificate) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Certificate certificate) throws Exception {
        int certificateNo = certificate.getNo();
        int fileNo = certificate.getImgFile().getNo();

        int result = certificateMapper.delete(certificateNo);
        result += fileService.delete(fileNo);

        return result;
    }



}
