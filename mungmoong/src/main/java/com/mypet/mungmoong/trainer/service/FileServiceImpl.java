package com.mypet.mungmoong.trainer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.mapper.FileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

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
        int result = fileMapper.delete(no);
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

    @Override
    public boolean upload(Files file) throws Exception {
        log.info("file : " + file);
        return true;
    }

    @Override
    public Files download(int no) throws Exception {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'download'");
    }


    
}
