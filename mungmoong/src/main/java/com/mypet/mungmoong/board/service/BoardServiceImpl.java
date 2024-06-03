package com.mypet.mungmoong.board.service;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
   BoardMapper boardMapper;

   @Override
    public List<Board> list() throws Exception {
        List<Board> boardList = boardMapper.list();
        return boardList;
    }

    /**
     * 게시글 조회
     * - no 매개변수로 게시글 번호를 전달받아서
     *   데이터베이스에 조회 요청
     */
    @Override
    public Board select(int no) throws Exception {
        Board board = boardMapper.select(no);
        return board;        
    }

    /**
     * 게시글 등록
     */
    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        return result;
    }

    /**
     * 게시글 수정
     */
    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result;
    }

    /**
     * 게시글 삭제
     */
    @Override
    public int BoardDelete(int no) throws Exception {
        int result = boardMapper.BoardDelete(no);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = boardMapper.BoardDelete(no);
        return result;
    }

    @Override
    public List<Board> checkDelete(int no) throws Exception {

        List<Board> deleteList = boardMapper.checkDelete(no);

        return deleteList;

    }

}
