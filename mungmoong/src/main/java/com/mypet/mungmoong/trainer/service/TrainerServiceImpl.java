package com.mypet.mungmoong.trainer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.mapper.TrainerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service        // 서비스 역할의 스프링 빈 등록
public class TrainerServiceImpl implements TrainerService {
    
    @Autowired
    private TrainerMapper TrainerMapper;
    
    @Autowired
    private FileService fileService;


    /**
     * 게시글 조회
     * - no 매개변수로 게시글 번호를 전달받아서 데이터베이스에 조회 요청
     */
    @Override
    public Trainer select(int no) throws Exception {
        // TrainerMapper 로 select(no) 호출
        /*
         *        ➡ trainer trainer 로 받아옴
         *        ➡ return trainer
         */
        Trainer trainer = TrainerMapper.select(no);
        // 추가 작업 •••
        // 만일 추가 작업이 없다면 바로 return에 넣어도 됨
        
        // TrainerMapper.view(no);

        return trainer;
    }

    /**
     * 게시글 등록
     */
    @Override
    public int insert(Trainer trainer) throws Exception {
        // TrainerMapper 로 insert(trainer) 호출
        /*
        *        ➡ int result 로 데이터 처리 행(개수) 받아옴
        *        ➡ return result
        */
        int result = TrainerMapper.insert(trainer);
        
        // 파일 업로드
        String parentTable = "trainer";
        int parentNo = TrainerMapper.maxPk();
        
        // 썸네일 업로드 (한 건)
        // - 부모테이블, 부모번호, 멀티파트파일, 파일코드:1(썸네일)
        MultipartFile thumbnailFile = trainer.getThumbnail();

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
        // List<MultipartFile> fileList = trainer.getFile();
        // if( !fileList.isEmpty() ) {
        //     for (MultipartFile file : fileList) {
        //         if( file.isEmpty() ) continue;
                
        //         // 파일 업로드 요청
        //         Files uploadFile = new Files();
        //         uploadFile.setParentTable(parentTable);
        //         uploadFile.setParentNo(parentNo);
        //         uploadFile.setFile(file);

        //         fileService.upload(uploadFile);
        //     }
        // }

        return result;
    }

    /**
     * 게시글 수정
     */
    @Override
    public int update(Trainer trainer) throws Exception {
        // TrainerMapper 로 update(trainer) 호출
        /*
         *        ➡ int result 로 데이터 처리 행(개수) 받아옴
         *        ➡ return result
         */
        int result = TrainerMapper.update(trainer);
        return result;
    }


}
