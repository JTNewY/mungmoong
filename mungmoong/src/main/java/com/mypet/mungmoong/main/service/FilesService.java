package com.mypet.mungmoong.main.service;

import java.util.List;

import com.mypet.mungmoong.main.model.Files;



public interface FilesService {

    public List<Files> list() throws Exception;

    public Files select(String id) throws Exception;

    public int insert(Files files) throws Exception;

    public int update(Files files) throws Exception;

    public int delete(String id) throws Exception;

    // 파일 업로드
    public boolean upload(Files file) throws Exception;
    // 파일 목록 - 부모 기준
    public List<Files> listByParent(Files file) throws Exception;
    // 파일 삭제 - 부모 기준
    public int deleteByParent(Files file) throws Exception;
    
    
}
