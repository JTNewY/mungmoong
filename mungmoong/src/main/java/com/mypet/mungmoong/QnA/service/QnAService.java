package com.mypet.mungmoong.QnA.service;

import java.util.List;


import com.mypet.mungmoong.QnA.dto.QnA;

public interface QnAService {
    
    public List<QnA> list() throws Exception; 

    public QnA select(int no) throws Exception;

    public int insert(QnA qna) throws Exception;

    public int update(QnA qna) throws Exception;

    public int delete(int no) throws Exception;

}
