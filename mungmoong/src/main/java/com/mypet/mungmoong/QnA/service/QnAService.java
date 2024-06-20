package com.mypet.mungmoong.QnA.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mypet.mungmoong.QnA.dto.QnA;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

public interface QnAService {
    
    public List<QnA> list(@Param("page") Page page
                           ,@Param("option") Option option) throws Exception;

    public QnA select(int no) throws Exception;

    public int insert(QnA qna) throws Exception;

    public int update(QnA qna) throws Exception;

    public int delete(int no) throws Exception;

    public int count(@Param("option") Option option) throws Exception;
    
    public List<QnA> search(Option option) throws Exception;

}
