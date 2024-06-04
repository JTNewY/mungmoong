package com.mypet.mungmoong.trainer.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private FileMapper fileMapper;

    // 파일 목록
    @Override
    public List<Files> list() throws Exception {
        List<Files> fileList = fileMapper.list();
        return fileList;
    }

    // 파일 조회
    @Override
    public Files select(int no) throws Exception {
        Files file = fileMapper.select(no);
        return file;
    }

    // 파일 등록
    @Override
    public int insert(Files file) throws Exception {
        int result = fileMapper.insert(file);
        return result;
    }

    // 파일 수정
    @Override
    public int update(Files file) throws Exception {
        int result = fileMapper.update(file);
        return result;
    }

    // 파일 삭제
    @Override
    public int delete(int no) throws Exception {
        // 파일 정보 조회
        Files file = fileMapper.select(no);

        // DB 파일 정보 삭제
        int result = fileMapper.delete(no);

        // 파일 시스템의 실제 파일 삭제
        if(result > 0) {
            String filePath = file.getFilePath();
            File deleteFile = new File(filePath);
            // 파일 존재 확인
            if( !deleteFile.exists() ) {
                return result;
            }
            // 파일 삭제
            if( deleteFile.delete() ) {
                log.info("파일이 정상적으로 삭제되었습니다.");
                log.info("file : " + filePath);
            } else {
                log.info("파일 삭제에 실패하였습니다.");
            }
        }
        return result;
    }

    @Override
    public Files selectByParent(Files file) throws Exception {
        Files result = fileMapper.selectByParent(file);
        return result;
    }
    

    // 부모 테이블 기준으로 파일 목록 조회
    @Override
    public List<Files> listByParent(Files file) throws Exception {
        List<Files> fileList = fileMapper.listByParent(file);
        return fileList;
    }

    // 부모 테이블 기준으로 파일 목록 삭제
    @Override
    public int deleteByParent(Files file) throws Exception {
        int result = fileMapper.deleteByParent(file);
        return result;

    }

     /**
     * 파일 업로드
     */
    @Override
    public boolean upload(Files file) throws Exception {
        log.info("file : " + file);

        MultipartFile mf = file.getFile();
        // 파일 정보 : 원본 파일명, 파일 용량, 파일 데이터
        String originName = mf.getOriginalFilename();
        long fileSize = mf.getSize();
        byte[] fileData = mf.getBytes();

        log.info("원본 파일명 : " + originName);
        log.info("파일 용량 : " + fileSize);
        log.info("파일 데이터 : " + fileData);

        // ⭐ 파일 업로드
        // - 파일 시스템의 해당 파일을 복사
        // - 파일의 정보를 DB에 등록
        
        // ✅ 업로드 경로   - application.properties ( upload.path )
        // ✅ 파일명        
        // - 파일명 중복 방지를 위해 UID_파일명.xxx 형식으로 지정
        // - 업로드 파일명 : UID_원본파일명.xxx
        String fileName = UUID.randomUUID().toString() + "_" + originName;
        File uploadFile = new File(uploadPath, fileName);

        // ⬆ 파일 업로드                
        FileCopyUtils.copy(fileData, uploadFile);

        // 파일 정보 등록
        file.setFileName(fileName);
        // filePath C:/upload/UID_원본파일명.xxx
        String filePath = uploadPath + "/" + fileName; 
        file.setFilePath(filePath);
        file.setFileSize(fileSize);
        // ⭐ 넘겨받을 때 세팅함.
        // file.setFileCode(0);
        insert(file);

        return true;
    }

    

    @Override
    public Files download(int no) throws Exception {
       return download(no);
    }

    

    
}