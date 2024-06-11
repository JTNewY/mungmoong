package com.mypet.mungmoong.imgfile.controller;

import com.mypet.mungmoong.imgfile.dto.ImgFileDTO;
import com.mypet.mungmoong.imgfile.service.ImgFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

@Controller
public class ImgFileController {

    private final ImgFileService imgFileService;

    @Autowired
    public ImgFileController(ImgFileService imgFileService) {
        this.imgFileService = imgFileService;
    }

    @GetMapping("/users/uploadForm")
    public String showUploadForm() {
        return "uploadForm";
    }

    @PostMapping("/users/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            String uploadDir = "src/main/resources/static/img/users/";
            String fileName = file.getOriginalFilename();
            File saveFile = new File(uploadDir + fileName);

            try {
                file.transferTo(saveFile);

                ImgFileDTO imgFileDTO = new ImgFileDTO();
                imgFileDTO.setParentNo(0); // 예: 실제 부모 ID로 변경 필요
                imgFileDTO.setParentTable("user_profile");
                imgFileDTO.setFileName(fileName);
                imgFileDTO.setFilePath("/img/users/" + fileName);
                imgFileDTO.setFileSize(file.getSize());
                imgFileDTO.setFileCode("0"); // 예: 코드 값 설정 필요
                imgFileDTO.setRegDate(new Timestamp(System.currentTimeMillis()));
                imgFileDTO.setUpdDate(new Timestamp(System.currentTimeMillis()));

                imgFileService.saveFile(imgFileDTO);

                model.addAttribute("message", "파일 업로드 성공");
                model.addAttribute("imgFile", imgFileDTO);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "파일 업로드 실패: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "업로드할 파일을 선택하세요");
        }
        return "redirect:/users/index";
    }
}
