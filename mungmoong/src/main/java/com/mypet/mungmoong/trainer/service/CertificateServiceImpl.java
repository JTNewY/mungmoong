package com.mypet.mungmoong.trainer.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public List<Certificate> select(int userId) throws Exception {
        List<Certificate> certificateList = certificateMapper.select(userId);
        return certificateList;
    }

    @Override
    public int insert(Certificate certificate) throws Exception {
        int result = certificateMapper.insert(certificate);

        // // 자격증 번호 가져오기
        // int certificateNo = certificateMapper.maxPk();
        // certificate.setNo(certificateNo);

        // // 파일 업로드
        // List<MultipartFile> fileList = certificate.getFile();
        // if (fileList != null && !fileList.isEmpty()) {
        //     for (MultipartFile file : fileList) {
        //         if (file.isEmpty()) continue;

        //         Files uploadFile = new Files();
        //         String parentTable = "certificate";
        //         uploadFile.setParentTable(parentTable);
        //         uploadFile.setParentNo(certificateNo);
        //         uploadFile.setFile(file);

        //         fileService.upload(uploadFile);
        //     }
        // }

        return result;

    }

    @Override
    public int update(Certificate certificate) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }



}
