package com.mypet.mungmoong.pet.service;

import java.util.List; 
import com.mypet.mungmoong.pet.dto.Pet; 

public interface PetService {

    void insertPet(Pet pet); // 새로운 펫을 추가하는 메서드 선언

    void updatePet(Pet pet); // 기존 펫 정보를 업데이트하는 메서드 선언

    void deletePet(int petNo); // 펫 번호로 펫을 삭제하는 메서드 선언

    Pet findPetById(int petNo); // 펫 번호로 펫 정보를 찾는 메서드 선언

    List<Pet> findPetByUserId(String userId); // 사용자 ID로 펫 목록을 찾는 메서드 선언

}


