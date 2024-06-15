package com.mypet.mungmoong.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.board.dto.Board;
import com.mypet.mungmoong.board.mapper.BoardMapper;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
   BoardMapper boardMapper;

   
    /**
     * 게시글 목록 조회
     */
    @Override
    public List<Board> list(Page page, Option option) throws Exception {
        // 게시글 데이터 개수 조회
        int total = boardMapper.count(option);
        page.setTotal(total);

        List<Board> boardList = boardMapper.list(page, option);
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
        int result = boardMapper.boardUpdate(board);
        return result;
    }

    /**
     * 게시글 삭제
     */
    @Override
    public int delete(int no) throws Exception {
        /*
         *        ➡ int result 로 데이터 처리 행(개수) 받아옴
         *        ➡ return result
         */
        int result = boardMapper.delete(no);

        return result;
    }

    @Override
    public int BoardDelete(int no) throws Exception {
        int result = boardMapper.BoardDelete(no);
        return result;
    }

    
    @Override
    public int SelectDelete(String[] SelectNoList) throws Exception {
        String DeleteNos = String.join(",", SelectNoList);
        int result = boardMapper.SelectDelete(DeleteNos);
        return result;
    }


    @Override
    // public List<Board> search(String keyword) throws Exception {
    public List<Board> search(Option option) throws Exception {
        
        // List<Board> boardList = boardMapper.search(keyword);
        List<Board> boardList = boardMapper.search(option);
        return boardList;
    }


}


  



    // @Override
    // public int delete(int no) throws Exception {
    //     int result = boardMapper.BoardDelete(no);
    //     return result;
    // }

    // @Override
    // public List<Board> checkDelete(int no) throws Exception {

    //     List<Board> deleteList = boardMapper.checkDelete(no);

    //     return deleteList;

    // }


