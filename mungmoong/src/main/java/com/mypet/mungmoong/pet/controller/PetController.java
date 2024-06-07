package com.mypet.mungmoong.pet.controller;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class PetController {

    @Autowired
    private PetService petService;

    // ####################################################펫 수정#######################################################

    @GetMapping("/users/petupdate")
    public String showPetUpdateForm(@RequestParam("petNo") int petNo, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // 로그인 페이지로 리디렉션
        }
    
        Pet pet = petService.findPetById(petNo);
        if (pet == null || !userId.equals(pet.getUserId())) {
            return "redirect:/users/index"; // 펫이 없거나 사용자가 다르면 리디렉션
        }
    
        model.addAttribute("pet", pet);
        return "users/petupdate"; // petupdate.html 템플릿을 렌더링
    }

    @PostMapping("/users/petupdate")
    public String updatePet(@RequestParam("petname") String petname,
                            @RequestParam("age") int age,
                            @RequestParam("gender") int gender,
                            @RequestParam("character") String character,
                            HttpSession session) {
                                
        // 세션에서 petNo 가져오기
        Integer petNo = (Integer) session.getAttribute("petNo");
        if (petNo == null) {
            return "redirect:/users/index"; // petNo가 세션에 없으면 리디렉션
        }

        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // 로그인 페이지로 리디렉션
        }

        Pet pet = petService.findPetById(petNo);
        if (pet == null || !userId.equals(pet.getUserId())) {
            return "redirect:/users/index"; // 펫이 없거나 사용자가 다르면 리디렉션
        }

        pet.setPetname(petname);
        pet.setAge(age);
        pet.setPetgender(gender);
        pet.setCharacter(character);
        petService.updatePet(pet);

        // 세션에서 petNo 제거
        session.removeAttribute("petNo");

        return "redirect:/users/index";
    }


    // ####################################################펫 추가#######################################################

    @PostMapping("/users/petadd")
        public String addPet(@RequestParam("petname") String petname,
                        @RequestParam("age") int age,
                        @RequestParam("gender") int gender,
                        @RequestParam("character") String character,
                        HttpSession session) {

        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login"; // 로그인 페이지로 리디렉션
        }

        Pet pet = new Pet();
        pet.setPetname(petname);
        pet.setAge(age);
        pet.setPetgender(gender);
        pet.setCharacter(character);
        pet.setUserId(userId); // 현재 사용자 ID 설정

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
