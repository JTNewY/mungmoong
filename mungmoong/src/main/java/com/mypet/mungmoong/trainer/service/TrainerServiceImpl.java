package com.mypet.mungmoong.trainer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.mungmoong.trainer.dto.Career;
import com.mypet.mungmoong.trainer.dto.Certificate;
import com.mypet.mungmoong.trainer.dto.Files;
import com.mypet.mungmoong.trainer.dto.Option;
import com.mypet.mungmoong.trainer.dto.Page;
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
            // 사용자 ID 가져옴
            String userId = trainer.getUserId();
            log.debug("Inserting trainer with userId: {}", userId);
    
            // 사용자 정보 조회
            Users user = usersService.select(userId);
            if (user == null) {
                throw new Exception("User not found"); 
            }
    
            // 기존 훈련사 정보를 조회하여 이미 존재하는 경우 stop
            Trainer oldTrainer = trainerMapper.select(userId);
            if (oldTrainer != null) return 0;
    
            // 새로운 훈련사 정보를 삽입하고 결과 반환
            int result = trainerMapper.insert(trainer);
            if (result == 0) {
                return 0; 
            }
    
            // 삽입된 훈련사의 번호를 가져와 설정
            int trainerNo = trainer.getNo();
            log.debug("Inserted trainer with trainerNo: {}", trainerNo);
            trainer.setNo(trainerNo);
    
            // 사용자의 역할을 훈련 승인중으로 변경하고 업데이트
            user.setRole(1);
            usersService.update(user);
    
            // 훈련사의 경력 목록을 가져와 각각의 경력을 삽입
            List<Career> careers = trainer.getCareerList();
            if (careers != null) {
                for (Career career : careers) {
                    // 각 경력 객체의 trainerNo 속성을 설정
                    career.setTrainerNo(trainerNo);
                    log.debug("Inserting career: {}", career);
                    careerService.insert(career); // 경력 정보를 삽입
                }
            }
    
            // 훈련사의 자격증 목록을 가져와 각각의 자격증 삽입
            List<Certificate> certificates = trainer.getCertificateList();
            if (certificates != null) {
                for (Certificate certificate : certificates) {
                    certificate.setTrainerNo(trainerNo);
                    log.debug("Inserting certificate: {}", certificate);
                    certificateService.insert(certificate); // 자격증 정보를 삽입
    
                    // 자격증과 연결된 파일 업로드
                    MultipartFile file = trainer.getFiles().get(certificates.indexOf(certificate));
                    if (file != null && !file.isEmpty()) {
                        Files uploadFile = new Files();
                        uploadFile.setParentTable("certificate");
                        uploadFile.setParentNo(certificate.getNo());
                        uploadFile.setFileName(file.getOriginalFilename());
                        uploadFile.setFilePath("C:/upload/" + file.getOriginalFilename());
                        uploadFile.setFileSize(file.getSize());
                        uploadFile.setFile(file);
                        fileService.upload(uploadFile); // 파일 업로드
                    }
                }
            }
    
            // 썸네일 파일이 있는 경우 업로드
            MultipartFile thumbnailFile = trainer.getThumbnail();
            if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
                Files thumbnail = new Files();
                thumbnail.setParentTable("trainer");
                thumbnail.setParentNo(trainerNo);
                thumbnail.setFileName(thumbnailFile.getOriginalFilename());
                thumbnail.setFilePath("C:/upload/" + thumbnailFile.getOriginalFilename());
                thumbnail.setFileSize(thumbnailFile.getSize());
                thumbnail.setFile(thumbnailFile);
                thumbnail.setFileCode(1); // 썸네일 파일 코드를 설정
                fileService.upload(thumbnail); // 썸네일 파일을 업로드
            }
    
            return result;
        } catch (Exception e) {
            log.error("Error occurred while inserting trainer data", e);
            throw e;
        }
    }
    

    @Override
    public int update(Trainer trainer) throws Exception {
        try {
            String userId = trainer.getUserId();


    
            Trainer existingTrainer = trainerMapper.select(userId);
            if (existingTrainer == null) {
                throw new Exception("Trainer not found");
            }
            int trainerNo = existingTrainer.getNo();
            trainer.setNo(trainerNo);
    
            int result = trainerMapper.update(trainer);
            if (result == 0) {
                return 0;
            }
    
            // Career : 수정 / 추가
            List<Career> careers = trainer.getCareerList();



            // log.info(careers.toString());
            
            if (careers != null) {
                for (Career career : careers) {
                    career.setTrainerNo(trainerNo);
                    int careerNo =  career.getNo();
                    if (careerNo == 0) {
                        careerService.insert(career);
                    } else {
                        careerService.update(career);
                    }
                }
            }




            // List<Certificate> certificates = trainer.getCertificateList();
            
            // if ( certificates != null ) {
            //     for (Certificate certificate : certificates) {
            //         certificate.setTrainerNo(trainerNo);
            //         log.debug("Inserting certificate : {}", certificate);
            //         certificateService.update(certificate);
                    
            //         //
            //         List<MultipartFile> file = trainer.getFiles();
            //         if ( file != null && file.isEmpty() ) {
            //             Files fileImg = new Files();
            //             fileImg.setParentTable("certificate");
            //             fileImg.setParentNo(certificate.getNo());
            //             fileImg.setFileName(fileImg.getFileName());
            //             fileImg.setFilePath("C:/upload/" + fileImg.getFileName());
            //             fileImg.setFileSize(fileImg.getFileSize());
            //             // fileImg.setFile(trainer.setFiles(imgFile));
            //             fileImg.setFileCode(0);
            //             fileService.update(fileImg);
            //         }

            //     }
            // }
            // if (file != null && !file.isEmpty()) {
            //     Files uploadFile = new Files();
            //     uploadFile.setParentTable("certificate");
            //     uploadFile.setParentNo(certificate.getNo());
            //     uploadFile.setFileName(file.getOriginalFilename());
            //     uploadFile.setFilePath("C:/upload/" + file.getOriginalFilename());
            //     uploadFile.setFileSize(file.getSize());
            //     uploadFile.setFile(file);
            //     fileService.upload(uploadFile); // 파일 업로드

            // Files imgFile = (Files) trainer.getFiles();
            // if (imgFile != null && !((MultipartFile) imgFile).isEmpty()) {
            //     Files files = new Files();
            //     files.setParentTable("trainer");
            //     files.setParentNo(trainerNo);
            //     files.setFileName(imgFile.getFileName());
            //     files.setFilePath("C:/upload/" + imgFile.getFilePath());
            //     files.setFileSize(imgFile.getFileSize());
            //     files.setFile((MultipartFile) files);
            //     files.setFileCode(0); // 썸네일 파일 코드를 설정
                
            // }
    
            return result;
        } catch (Exception e) {
            log.error("Error occurred while updating trainer data", e);
            throw e;
        }
    }
    

    @Override
    public List<Trainer> trainerList(Page page, Option option) throws Exception {

        int total = trainerMapper.count(option);
        page.setTotal(total);

        List<Trainer> trainerList = trainerMapper.trainerList(page, option);

        for (Trainer trainer : trainerList) {               /* foreach문으로 트레이너에 트레이너 리스트를 하나하나 가져옴 */
            String userId = trainer.getUserId();            /* trainer의 userId를 가져와서 userId변수에 담음 */
            Users user = usersService.select(userId);       /* userService.select에 userId를 담아서 users를 조회 */
            trainer.setUser(user);                          /* 조회된 users값을 trainer에 세팅 함 */
        }

        return trainerList;

    }

    




    @Override
    public int selectTrainerNo() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectTrainerNo'");
    }




    @Override
    public List<Trainer> adminTrainerList(Page page, Option option) throws Exception {

        List<Trainer> trainerList = trainerMapper.trainerList(page, option);


        for (Trainer trainer : trainerList) {               /* foreach문으로 트레이너에 트레이너 리스트를 하나하나 가져옴 */
            String userId = trainer.getUserId();            /* trainer의 userId를 가져와서 userId변수에 담음 */
            Users user = usersService.select(userId);       /* userService.select에 userId를 담아서 users를 조회 */
            trainer.setUser(user);                          /* 조회된 users값을 trainer에 세팅 함 */
        }
        return trainerList;

    }




    @Override
    public Trainer selectByNo(int trainerNo) {
        Trainer trainer = trainerMapper.selectByNo(trainerNo);
        return trainer;
    }



    // @Override
    // public List<Trainer> trainerList() throws Exception {
    //     List<Trainer> trainerList = trainerMapper.trainerList();

    //     for (Trainer trainer : trainerList) {               /* foreach문으로 트레이너에 트레이너 리스트를 하나하나 가져옴 */
    //         String userId = trainer.getUserId();            /* trainer의 userId를 가져와서 userId변수에 담음 */
    //         Users user = usersService.select(userId);       /* userService.select에 userId를 담아서 users를 조회 */
    //         trainer.setUser(user);                          /* 조회된 users값을 trainer에 세팅 함 */
    //     }

    //     return trainerList;
    // }
}