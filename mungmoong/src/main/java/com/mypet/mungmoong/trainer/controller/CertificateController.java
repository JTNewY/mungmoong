package com.mypet.mungmoong.trainer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.service.CertificateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @DeleteMapping("")
    public ResponseEntity<String> deleteCertificate(@RequestParam("no") int certificateNo
                                                   ,@RequestParam("fileNo") int fileNo
                                                   ,Certificate certificate
                                                   ,Files file) throws Exception {
        log.info("certificateNo : " + certificateNo);
        log.info("fileNo : " + fileNo);
        file.setNo(fileNo);
        certificate.setNo(certificateNo);
        certificate.setImgFile(file);
        int result = certificateService.delete(certificate);
        if( result > 0 ) {
            return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("FAIL",HttpStatus.OK);
        }
    }
    
}
