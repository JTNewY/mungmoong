package com.mypet.mungmoong.imgfile.service;

import com.mypet.mungmoong.imgfile.dto.ImgFileDTO;
import java.util.List;

/**
 * ImgFileService 인터페이스는 이미지 파일에 대한 서비스 메소드를 정의합니다.
 */
public interface ImgFileService {
    
    /**
     * 특정 ID를 가진 이미지 파일을 조회합니다.
     * @param no 이미지 파일의 ID
     * @return ImgFileDTO 객체
     */
    ImgFileDTO getFileById(int no);
    
    /**
     * 모든 이미지 파일을 조회합니다.
     * @return ImgFileDTO 객체의 리스트
     */
    List<ImgFileDTO> getAllFiles();
    
    /**
     * 새로운 이미지 파일을 저장합니다.
     * @param imgFileDTO 저장할 이미지 파일 정보
     */
    void saveFile(ImgFileDTO imgFileDTO);
    
    /**
     * 기존의 이미지 파일 정보를 수정합니다.
     * @param imgFileDTO 수정할 이미지 파일 정보
     */
    void updateFile(ImgFileDTO imgFileDTO);
    
    /**
     * 특정 ID를 가진 이미지 파일을 삭제합니다.
     * @param no 삭제할 이미지 파일의 ID
     */
    void deleteFile(int no);
}
