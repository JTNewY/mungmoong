package com.mypet.mungmoong.imgfile.mapper;

import com.mypet.mungmoong.imgfile.dto.ImgFileDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ImgFileMapper {
    ImgFileDTO selectFileById(int no);

    List<ImgFileDTO> selectAllFiles();

    void insertFile(ImgFileDTO imgFileDTO);

    void updateFile(ImgFileDTO imgFileDTO);
    
    void deleteFile(int no);
}
