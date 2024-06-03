package com.mypet.mungmoong.trainer.service;

import java.util.List;

import com.mypet.mungmoong.trainer.dto.Files;

public interface FileService {

    // 파일 목록
    public List<Files> list() throws Exception;
    // 파일 조회
    public Files select(int no) throws Exception;
    // 파일 등록
    public int insert(Files file) throws Exception;
    // 파일 수정
    public int update(Files file) throws Exception;
    // 파일 삭제
    public int delete(int no) throws Exception;

    // 파일 목록 - 부모 기준
    public List<Files> listByParent(Files thumbnail) throws Exception;
    // 파일 삭제 - 부모 기준
    public int deleteByParent(Files file) throws Exception;
    
    // 파일 업로드
    public boolean upload(Files thumbnail) throws Exception;
    // 파일 다운로드
    public Files download(int no) throws Exception;
    
}
 