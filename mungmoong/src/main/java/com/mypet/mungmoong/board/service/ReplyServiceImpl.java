package com.mypet.mungmoong.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.board.dto.Reply;
import com.mypet.mungmoong.board.mapper.ReplyMapper;


@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> list() throws Exception {
        List<Reply> replyList = replyMapper.list();
        return replyList;
    }

    @Override
    public Reply select(int no) throws Exception {
        Reply reply = replyMapper.select(no);
        return reply;
    }

    @Override
    public int insert(Reply reply) throws Exception {
        int result = replyMapper.insert(reply);

        return result;
    }

    @Override
    public int update(Reply reply) throws Exception {
        int result = replyMapper.update(reply);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = replyMapper.delete(no);

        // ⭐ 자식 답글 삭제
        if( result > 0 ) {
            result += deleteByParentNo(no);
        }
        return result;
    }

    @Override
    public List<Reply> listByBoardNo(int boardNo) throws Exception {
        List<Reply> replyList = replyMapper.listByBoardNo(boardNo);
        return replyList;
    }

    @Override
    public int deleteByBoardNo(int boardNo) throws Exception {
        int result = replyMapper.deleteByBoardNo(boardNo);
        return result;
    }

    @Override
    public int max() throws Exception {
        int max = replyMapper.max();
        return max;
    }

    @Override
    public int deleteByParentNo(int parentNo) throws Exception {
        int result = replyMapper.deleteByParentNo(parentNo);
        return result;
    }

    @Override
    public List<Reply> listByParent(Reply reply) throws Exception {
        List<Reply> replyList = replyMapper.listByParent(reply);
        return replyList;
    }
    


}
