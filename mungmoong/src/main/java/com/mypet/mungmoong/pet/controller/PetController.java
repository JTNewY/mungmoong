package com.mypet.mungmoong.pet.controller;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);


    @Autowired
    private PetService petService;
    

    // ####################################################펫 수정#######################################################
    
    @GetMapping("/users/petUpdate")
    public String showUpdatePetForm(@RequestParam(name = "petNo", required = true) Integer petNo, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        logger.info("Received request for petNo: {}", petNo);
        logger.info("userId from session: {}", userId);
        session.getAttributeNames().asIterator().forEachRemaining(name -> logger.info("Session attribute: {} = {}", name, session.getAttribute(name)));
    
        if (petNo == null) {
            logger.error("Missing petNo parameter");
            return "redirect:/users/index?error=MissingPetNo";
        }
    
        Pet pet = petService.findPetById(petNo);
        if (pet == null || !pet.getUserId().equals(userId)) {
            return "redirect:/users/index?error=PetNotFound";
        }
        model.addAttribute("pet", pet);
        model.addAttribute("petNo", petNo);  // Add petNo to model
        return "users/petUpdate";
    }
    
    @PostMapping("/users/petUpdate")
    public String updatePet(@RequestParam("petNo") int petNo,
                            @RequestParam("petname") String petname,
                            @RequestParam("age") int age,
                            @RequestParam("petgender") int petgender,  // 필드 이름 일관성 유지
                            @RequestParam("character") String character,
                            @RequestParam("type") String type,
                            @RequestParam("specialNotes") String specialNotes,
                            HttpSession session) {
    
        String userId = (String) session.getAttribute("userId");
        logger.info("Updating pet: userId={}, petNo={}", userId, petNo);
        session.getAttributeNames().asIterator().forEachRemaining(name -> logger.info("Session attribute: {} = {}", name, session.getAttribute(name)));
    
   
    
        Pet pet = petService.findPetById(petNo);
        if (pet == null || !pet.getUserId().equals(userId)) {
            logger.warn("Pet not found or user not authorized, redirecting to index page");
            return "redirect:/users/index?error=PetNotFound";
        }
    
        pet.setPetname(petname);
        pet.setAge(age);
        pet.setPetgender(petgender);
        pet.setCharacter(character);
        pet.setType(type);
        pet.setSpecialNotes(specialNotes);
        pet.setUpdDate(new Date());
    
        petService.updatePet(pet);
    
        return "redirect:/users/index?success=PetUpdated";
    }
    


    // ####################################################펫 추가#######################################################


   
  
    @GetMapping("/users/petAdd")
    public String showAddPetForm(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        logger.info("showAddPetForm called, session userId: {}", userId);
        if (userId == null) {
            logger.warn("User ID is null, redirecting to login");
            return "redirect:/users/login"; // 로그인 페이지로 리디렉션
        }
        model.addAttribute("userId", userId); // 템플릿에 userId를 전달합니다.
        return "users/petAdd"; // 템플릿 파일 경로를 반환합니다.
    }

    @PostMapping("/users/petAdd")
    public String addPet(@RequestParam("petname") String petname,
                         @RequestParam("age") int age,
                         @RequestParam("gender") int gender,
                         @RequestParam("character") String character,
                         @RequestParam("type") String type,
                         @RequestParam("specialNotes") String specialNotes,
                         HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        logger.info("addPet called, session userId: {}", userId);
        if (userId == null) {
            logger.warn("User not logged in, redirecting to login page");
            return "redirect:/users/login"; // 로그인 페이지로 리디렉션
        }

        Pet pet = new Pet();
        pet.setUserId(userId); // 현재 사용자 ID 설정
        pet.setPetname(petname);
        pet.setAge(age);
        pet.setPetgender(gender);
        pet.setCharacter(character);
        pet.setType(type);
        pet.setSpecialNotes(specialNotes); // 특이사항 추가함

        // 현재 시간을 설정
        pet.setRegDate(new Date());
        pet.setUpdDate(new Date());

        petService.addPet(pet);

        return "redirect:/users/index";
    }
}
