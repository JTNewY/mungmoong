package com.mypet.mungmoong.pet.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mypet.mungmoong.pet.dto.Pet;

@Mapper
public interface PetMapper {


    // 반려동물 추가
    void insertPet(Pet pet);

    // 반려동물 정보 수정
    void updatePet(Pet pet);

    // 반려동물 정보 삭제
    void deletePet(int petNo);

    // 반려동물 정보 검색
    Pet findPetById(int petNo);

     // 사용자 ID로 반려동물 목록 조회
    List<Pet> findPetsByUserId(String userId);


    
}
