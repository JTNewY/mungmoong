package com.mypet.mungmoong.trainer.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.trainer.dto.Certificate;

@Mapper     // Mybatis의 매퍼 인터페이스로 지정하는 어노테이션
public interface CertificateMapper {

    // 자격증 조회 리스트
    public List<Certificate> listByUserId(String userId);
    // 자격증 등록
    public int insert(Certificate certificate) throws Exception;
    // 자격증 수정
    public int update(Certificate certificate) throws Exception;
    // 자격증 삭제
    public int delete(int no) throws Exception;
    
    // 자격증 번호(기본키) 최댓값        
    public int maxPk() throws Exception;

    // 자격증 데이터 개수 조회

    // public int count(@RequestParam("option") Option option) throws Exception;

    // 자격증 목록 - [검색]
    // 매개변수 하나가 들어온다면 xml의 keyword가 text 등이여도 받아올 수 있다.
    // public List<Board> search(@Param("keyword") String keyword) throws Exception;
    // public List<Trainer> search(/* @Param("option") Option option */) throws Exception;


    
}