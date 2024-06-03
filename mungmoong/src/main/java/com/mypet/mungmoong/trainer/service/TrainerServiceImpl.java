package com.mypet.mungmoong.trainer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.trainer.dto.Career;
import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Trainer;
import com.mypet.mungmoong.trainer.mapper.TrainerMapper;
import com.mypet.mungmoong.users.dto.Users;
import com.mypet.mungmoong.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private UsersService usersService;

    @Override
    public Trainer select(String userId) throws Exception {
        Trainer trainer = trainerMapper.select(userId);

        int no = trainer.getNo();
        Files file = new Files();
        file.setParentTable("trainer");
        file.setParentNo(no);
        Files imgFile = fileService.selectByParent(file);
        log.info("프로필 이미지 : " + imgFile);
        trainer.setImgFile(imgFile);

        return trainer;
    }

    @Override
    public int insert(Trainer trainer) throws Exception {
        try {
            String userId = trainer.getUserId();
            log.debug("Inserting trainer with userId: {}", userId);

            Users user = usersService.select(userId);
            if (user == null) {
                throw new Exception("User not found");
            }

            Trainer oldTrainer = trainerMapper.select(userId);
            if (oldTrainer != null) return 0;

            int result = trainerMapper.insert(trainer);
            if (result == 0) {
                return 0;
            }

            int trainerNo = trainer.getNo();
            log.debug("Inserted trainer with trainerNo: {}", trainerNo);
            trainer.setNo(trainerNo);

            user.setRole(1);
            usersService.update(user);

            List<Career> careers = trainer.getCareerList();
            if (careers != null) {
                for (Career career : careers) {
                    career.setTrainerNo(trainerNo);
                    log.debug("Inserting career: {}", career);
                    careerService.insert(career);
                }
            }

            List<Certificate> certificates = trainer.getCertificateList();
            if (certificates != null) {
                for (Certificate certificate : certificates) {
                    certificate.setTrainerNo(trainerNo);
                    log.debug("Inserting certificate: {}", certificate);
                    certificateService.insert(certificate);

                    MultipartFile file = trainer.getFiles().get(certificates.indexOf(certificate));
                    if (file != null && !file.isEmpty()) {
                        Files uploadFile = new Files();
                        uploadFile.setParentTable("certificate");
                        uploadFile.setParentNo(certificate.getNo());
                        uploadFile.setFileName(file.getOriginalFilename());
                        uploadFile.setFilePath("C:/upload/" + file.getOriginalFilename());
                        uploadFile.setFileSize(file.getSize());
                        uploadFile.setFile(file);
                        fileService.upload(uploadFile);
                    }
                }
            }

            MultipartFile thumbnailFile = trainer.getThumbnail();
            if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
                Files thumbnail = new Files();
                thumbnail.setParentTable("trainer");
                thumbnail.setParentNo(trainerNo);
                thumbnail.setFileName(thumbnailFile.getOriginalFilename());
                thumbnail.setFilePath("C:/upload/" + thumbnailFile.getOriginalFilename());
                thumbnail.setFileSize(thumbnailFile.getSize());
                thumbnail.setFile(thumbnailFile);
                thumbnail.setFileCode(1);
                fileService.upload(thumbnail);
            }

            return result;
        } catch (Exception e) {
            log.error("Error occurred while inserting trainer data", e);
            throw e;
        }
    }

    @Override
    public int update(Trainer trainer) throws Exception {
        int result = trainerMapper.update(trainer);
        return result;
    }

    @Override
    public List<Trainer> trainerList() throws Exception {
        List<Trainer> trainerList = trainerMapper.trainerList();

        for (Trainer trainer : trainerList) {
            String userId = trainer.getUserId();
            Users user = usersService.select(userId);
            trainer.setUser(user);
        }

        return trainerList;
    }
}
