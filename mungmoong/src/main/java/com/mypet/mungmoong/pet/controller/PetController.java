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
        logger.info("Received request for petNo: {}", petNo);  // 로그 추가
        logger.info("userId from session: {}", userId);
        session.getAttributeNames().asIterator().forEachRemaining(name -> logger.info("Session attribute: {} = {}", name, session.getAttribute(name)));
    
        if (userId == null) {
            return "redirect:/users/login";
        }
    
        if (petNo == null) {
            logger.error("Missing petNo parameter");
            return "redirect:/users/index?error=MissingPetNo";
        }
    
        Pet pet = petService.findPetById(petNo);
        if (pet == null || !pet.getUserId().equals(userId)) {
            return "redirect:/users/index?error=PetNotFound";
        }
        model.addAttribute("pet", pet);
        return "users/petUpdate";
    }
    

@PostMapping("/users/petUpdate")
public String updatePet(@RequestParam("pet_no") int petNo,
                        @RequestParam("petname") String petname,
                        @RequestParam("age") int age,
                        @RequestParam("petgender") int petgender,  // 필드 이름 일관성 유지
                        @RequestParam("character") String character,
                        @RequestParam("type") String type,
                        @RequestParam("specialNotes") String specialNotes,
                        HttpSession session) {

    String userId = (String) session.getAttribute("userId");
    logger.info("Updating pet: userId={}, petNo={}", userId, petNo);
    session.getAttributeNames().asIterator().forEachRemaining(Name -> logger.info("Session attribute: {} = {}", petname, session.getAttribute(petname)));

    if (userId == null) {
        logger.warn("User not logged in, redirecting to login page");
        return "redirect:/users/login";
    }

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
    public String showAddPetForm(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/users/login"; // 로그인 페이지로 리디렉션
        }
        return "redirect:/users/petAdd"; // 애완동물 추가 페이지로 이동
    }

    @PostMapping("/users/petAdd")
    public String addPet(@RequestParam("petname") String petname,
                         @RequestParam("age") int age,
                         @RequestParam("gender") int gender,
                         @RequestParam("character") String character,
                         @RequestParam("type") String type,
                         @RequestParam("specialNotes") String specialNotes,
                         @RequestParam("userId") String userId,
                         HttpSession session) {

    
        if (userId == null) {
            return "redirect:/users/login"; // 로그인 페이지로 리디렉션
        }

        Pet pet = new Pet();
        pet.setUserId(userId); // 현재 사용자 ID 설정
        pet.setPetname(petname);
        pet.setAge(age);
        pet.setPetgender(gender);
        pet.setCharacter(character);
        pet.setType(type);
        pet.setSpecialNotes(specialNotes);  // 특이사항 추가함

        // 현재 시간을 설정
        pet.setRegDate(new Date());
        pet.setUpdDate(new Date());

        petService.addPet(pet);

        return "redirect:/users/index";
    }


    // ####################################################펫 삭제#######################################################






    // ####################################################펫시터 이용내역#######################################################


    // @GetMapping("/users/details")
    // public String showUserDetails(HttpSession session, Model model) {
    //     String userId = (String) session.getAttribute("userId");
    //     if (userId == null) {
    //         return "redirect:/login"; // 로그인 페이지로 리디렉션
    //     }
    //     // 사용자의 펫시터 이용내역을 가져옵니다. (예시: 사용자 ID를 기반으로 가져온다고 가정)
    //     List<Reservation> reservations = reservationService.findReservationsByUserId(userId);
    //     model.addAttribute("reservations", reservations);
    
    //     return "users/details"; // details.html 템플릿을 렌더링
    // }

    }