package com.mypet.mungmoong.QnA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.QnA.dto.QnA;
import com.mypet.mungmoong.QnA.mapper.QnAMapper;
import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

@Service
public class QnAServiceImpl implements QnAService{

    @Autowired
    private QnAMapper qnaMapper;

    @Override
    public List<QnA> list(Page page, Option option) throws Exception {
        // 게시글 데이터 개수 조회
        int total = qnaMapper.count(option);
        page.setTotal(total);
      
        List<QnA> qnaList = qnaMapper.list(page, option);
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
    
    @Override
    // public List<Board> search(String keyword) throws Exception {
    public List<QnA> search(Option option) throws Exception {
        
        // List<Board> boardList = boardMapper.search(keyword);
        List<QnA> qnaList = qnaMapper.search(option);
        return qnaList;
    }

    @Override
    public int count(Option option) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }



}
