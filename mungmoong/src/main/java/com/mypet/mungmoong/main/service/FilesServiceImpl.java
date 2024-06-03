package com.mypet.mungmoong.main.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.main.mapper.FilesMapper;
import com.mypet.mungmoong.main.model.Files;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesMapper filesMapper;

    @Value("${upload.path}")        // application.properties 에 설정한 업로드 경로 가져옴
    private String uploadPath; 

    @Override
    public List<Files> list() throws Exception {
        return filesMapper.list();
    }

    @Override
    public Files select(String id) throws Exception {
        return filesMapper.select(id);
    }

    @Override
    public int insert(Files files) throws Exception {
        String id = UUID.randomUUID().toString();
        files.setId(id);
        int result = filesMapper.insert(files);
        return result;
    }

    @Override
    public int update(Files files) throws Exception {
        int result = filesMapper.update(files);
        return result;
    }

    /**
     * 파일 삭제
     */
    @Override
    public int delete(String id) throws Exception {
        // 파일 정보 조회
        Files file = filesMapper.select(id);

        // DB 파일 정보 삭제
        int result = filesMapper.delete(id);

        // 파일 시스템의 파일 삭제
        if( result > 0 ) {
            String path = file.getPath();
            File deleteFile = new File(path);
            // 파일 존재 확인
            if( !deleteFile.exists() ) {
                return result;
            }
            // 파일 삭제
            if( deleteFile.delete() ) {
                log.info("파일이 정상적으로 삭제 되었습니다.");
                log.info("file : " + path);
            } else {
                log.info("파일 삭제에 실패하였습니다.");
            }
        }
        return result;
    }

    /**
     * 부모 테이블 기준, 파일 목록 조회
     */
    @Override
    public List<Files> listByParent(Files file) throws Exception {
        List<Files> fileList = filesMapper.listByParent(file);
        log.info("file : " + file);
        return fileList;
    }

    /**
     * 부모 테이블에 종속된 파일 목록 삭제
     */
    @Override
    public int deleteByParent(Files file) throws Exception {
        List<Files> fileList = filesMapper.listByParent(file);
        
        for (Files deleteFile : fileList) {
            String id = deleteFile.getId();
            delete(id);
        }
        
        int result = filesMapper.deleteByParent(file);
        log.info(result + "개의 파일을 삭제하였습니다.");
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
        file.setName(fileName);
        file.setOriginName(originName);
        // filePath C:/upload/UID_원본파일명.xxx
        String filePath = uploadPath + "/" + fileName; 
        file.setPath(filePath);
        file.setSize(fileSize);
        // ⭐ 넘겨받을 때 세팅함.
        // file.setFileCode(0);
        insert(file);

        return true;
    }

    
}
