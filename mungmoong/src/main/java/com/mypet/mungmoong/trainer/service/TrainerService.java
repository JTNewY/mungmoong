package com.mypet.mungmoong.trainer.service;

import java.util.List;

import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

public interface TrainerService {

    // 게시글 목록
    public List<Trainer> list(Page page) throws Exception;     
    // public List<Trainer> list(Page page, Option option) throws Exception;     
    // 게시글 조회
    public Trainer select(int no) throws Exception;  // 이 메소드를 호출하는 곳으로 예외 전가
    // 게시글 등록
    public int insert(Trainer trainer) throws Exception;
    // 게시글 수정
    public int update(Trainer trainer) throws Exception;


    // 게시글 목록 - [검색]
    // public List<Trainer> search(String keyword) throws Exception;
    public List<Trainer> search(Option option) throws Exception;

    // 조회 수 증가
    public int view(int no) throws Exception;

    
}