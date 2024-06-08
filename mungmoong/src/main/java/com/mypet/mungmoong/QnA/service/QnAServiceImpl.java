package com.mypet.mungmoong.QnA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.QnA.dto.QnA;
import com.mypet.mungmoong.QnA.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService{

    @Autowired
    private QnAMapper qnaMapper;

    @Override
    public List<QnA> list() throws Exception {
        List<QnA> qnaList = qnaMapper.list();
        return qnaList;
    }

    @Override
    public QnA select(int no) throws Exception {
        QnA qna = qnaMapper.select(no);
        return qna;
    }

    @Override
    public int insert(QnA qna) throws Exception {
        int result = qnaMapper.insert(qna);
        return result;
    }

    @Override
    public int update(QnA qna) throws Exception {
        int result = qnaMapper.update(qna);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = qnaMapper.delete(no);
        return result;
    }
    
}
