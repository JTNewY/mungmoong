package com.mypet.mungmoong.trainer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.trainer.dto.Board;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
import com.mypet.mungmoong.trainer.mapper.TrainerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service        // 서비스 역할의 스프링 빈 등록
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private TrainerMapper trainerMapper;
    
    @Autowired
    private FileService fileService;


    /**
     * 게시글 목록 조회
     */
    @Override
    public List<Board> list(Page page, Option option) throws Exception {
        // 게시글 데이터 개수 조회
        int total = trainerMapper.count(option);
        page.setTotal(total);
        
        // trainerMapper 인터페이스 호출 -> trainerMapper.xml 호출
        List<Board> boardList = trainerMapper.list(page, option);
        return boardList;
    }

    /**
     * 게시글 조회
     * - no 매개변수로 게시글 번호를 전달받아서 데이터베이스에 조회 요청
     */
    @Override
    public Board select(int no) throws Exception {
        // trainerMapper 로 select(no) 호출
        /*
         *        ➡ Board board 로 받아옴
         *        ➡ return board
         */
        Board board = trainerMapper.select(no);
        // 추가 작업 •••
        // 만일 추가 작업이 없다면 바로 return에 넣어도 됨
        
        // trainerMapper.view(no);

        return board;
    }

    /**
     * 게시글 등록
     */
    @Override
    public int insert(Board board) throws Exception {
        // trainerMapper 로 insert(Board) 호출
        /*
        *        ➡ int result 로 데이터 처리 행(개수) 받아옴
        *        ➡ return result
        */
        int result = trainerMapper.insert(board);
        
        // 파일 업로드
        String parentTable = "board";
        int parentNo = trainerMapper.maxPk();
        
        // 썸네일 업로드 (한 건)
        // - 부모테이블, 부모번호, 멀티파트파일, 파일코드:1(썸네일)
        MultipartFile thumbnailFile = board.getThumbnail();

        // 썸네일 파일 업로드한 경우만 
        // (isEmpty -> 파일은 있지만, byte가 0인 경우 (껍데기 파일))
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFileCode(1);       // 썸네일 파일 코드(1)
            // 실제로는 upload를 타므로 여기도 확인!
            fileService.upload(thumbnail);           // 썸네일 파일 업로드
        }
        
        // 첨부파일 업로드 (한 건 이상)
        List<MultipartFile> fileList = board.getFile();
        if( !fileList.isEmpty() ) {
            for (MultipartFile file : fileList) {
                if( file.isEmpty() ) continue;
                
                // 파일 업로드 요청
                Files uploadFile = new Files();
                uploadFile.setParentTable(parentTable);
                uploadFile.setParentNo(parentNo);
                uploadFile.setFile(file);

                fileService.upload(uploadFile);
            }
        }

        return result;
    }

    /**
     * 게시글 수정
     */
    @Override
    public int update(Board board) throws Exception {
        // trainerMapper 로 update(Board) 호출
        /*
         *        ➡ int result 로 데이터 처리 행(개수) 받아옴
         *        ➡ return result
         */
        int result = trainerMapper.update(board);
        return result;
    }

    /**
     * 게시글 삭제
     */
    @Override
    public int delete(int no) throws Exception {
        int result = trainerMapper.delete(no);
        return result;
    }

    @Override
    public List<Board> search(Option option) throws Exception {
        List<Board> boardList = trainerMapper.search(option);
        return boardList;
    }

    /**
     * 조회 수 증가
     */
    @Override
    public int view(int no) throws Exception {
        log.info(no + "빈 글 조회 수 증가 •••");
        return trainerMapper.view(no);
    }


}
