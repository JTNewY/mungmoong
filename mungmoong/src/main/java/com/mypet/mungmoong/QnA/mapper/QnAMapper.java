package com.mypet.mungmoong.QnA.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.QnA.dto.QnA;

@Mapper
public interface QnAMapper {
    
    public List<QnA> list() throws Exception; 

    public QnA select(int no) throws Exception;

    public int insert(QnA qna) throws Exception;

    public int update(QnA qna) throws Exception;

    public int delete(int no) throws Exception;







}
