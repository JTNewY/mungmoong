package com.mypet.mungmoong.imgfile.service;

import com.mypet.mungmoong.imgfile.dto.ImgFileDTO;
import com.mypet.mungmoong.imgfile.mapper.ImgFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgFileServiceImpl implements ImgFileService {

    @Autowired
    private ImgFileMapper imgFileMapper;

    @Override
    public ImgFileDTO getFileById(int no) {
        return imgFileMapper.selectFileById(no);
    }

    @Override
    public List<ImgFileDTO> getAllFiles() {
        return imgFileMapper.selectAllFiles();
    }

    @Override
    public void saveFile(ImgFileDTO imgFileDTO) {
        imgFileMapper.insertFile(imgFileDTO);
    }

    @Override
    public void updateFile(ImgFileDTO imgFileDTO) {
        imgFileMapper.updateFile(imgFileDTO);
    }

    @Override
    public void deleteFile(int no) {
        imgFileMapper.deleteFile(no);
    }
}


// package com.mypet.mungmoong.imgfile.service;

// import com.mypet.mungmoong.imgfile.dto.ImgFileDTO;
// import com.mypet.mungmoong.imgfile.mapper.ImgFileMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.util.List;

// /**
//  * ImgFileServiceImpl 클래스는 ImgFileService 인터페이스를 구현한 클래스입니다.
//  * 이 클래스는 ImgFileMapper를 사용하여 데이터베이스와 상호 작용합니다.
//  */
// @Service
// public class ImgFileServiceImpl implements ImgFileService {

//     private final ImgFileMapper imgFileMapper;

//     /**
//      * ImgFileServiceImpl 생성자입니다. ImgFileMapper를 주입받습니다.
//      * @param imgFileMapper 주입받을 ImgFileMapper 객체
//      */
//     @Autowired
//     public ImgFileServiceImpl(ImgFileMapper imgFileMapper) {
//         this.imgFileMapper = imgFileMapper;
//     }

//     /**
//      * 특정 ID를 가진 이미지 파일을 조회합니다.
//      * @param no 이미지 파일의 ID
//      * @return 조회된 ImgFileDTO 객체
//      */
//     @Override
//     public ImgFileDTO getFileById(int no) {
//         return imgFileMapper.selectFileById(no);
//     }

//     /**
//      * 모든 이미지 파일을 조회합니다.
//      * @return 조회된 ImgFileDTO 객체의 리스트
//      */
//     @Override
//     public List<ImgFileDTO> getAllFiles() {
//         return imgFileMapper.selectAllFiles();
//     }

//     /**
//      * 새로운 이미지 파일을 저장합니다.
//      * @param imgFileDTO 저장할 이미지 파일 정보
//      */
//     @Override
//     public void saveFile(ImgFileDTO imgFileDTO) {
//         imgFileMapper.insertFile(imgFileDTO);
//     }

//     /**
//      * 기존의 이미지 파일 정보를 수정합니다.
//      * @param imgFileDTO 수정할 이미지 파일 정보
//      */
//     @Override
//     public void updateFile(ImgFileDTO imgFileDTO) {
//         imgFileMapper.updateFile(imgFileDTO);
//     }

//     /**
//      * 특정 ID를 가진 이미지 파일을 삭제합니다.
//      * @param no 삭제할 이미지 파일의 ID
//      */
//     @Override
//     public void deleteFile(int no) {
//         imgFileMapper.deleteFile(no);
//     }
// }
