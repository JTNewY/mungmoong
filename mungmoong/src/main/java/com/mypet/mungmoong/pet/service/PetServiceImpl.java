package com.mypet.mungmoong.pet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.mapper.PetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PetServiceImpl implements PetService {

    private static final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);
    private final PetMapper petMapper; // PetMapper를 주입받기 위한 변수 선언

    @Autowired 
    public PetServiceImpl(PetMapper petMapper) {
        this.petMapper = petMapper; // 생성자를 통해 주입된 PetMapper를 클래스 변수에 할당
    }

    @Override
    public void addPet(Pet pet) {
        petMapper.insertPet(pet); // 펫을 추가하기 위해 PetMapper의 insertPet 메서드 호출
    }

    @Override
    public void updatePet(Pet pet) {
        petMapper.updatePet(pet); // 펫 정보를 업데이트하기 위해 PetMapper의 updatePet 메서드 호출
    }

    @Override
    public void deletePet(int petNo) {
        petMapper.deletePet(petNo); // 펫을 삭제하기 위해 PetMapper의 deletePet 메서드 호출
    }

    @Override
    public Pet findPetById(int petNo) {
        return petMapper.findPetById(petNo); // 펫 번호로 펫을 찾기 위해 PetMapper의 findPetById 메서드 호출
    }

    @Override
    public List<Pet> findPetByUserId(String userId) {
        List<Pet> pets = petMapper.findPetsByUserId(userId);
        logger.info("Number of pets found for user {}: {}", userId, pets.size()); // 반환된 펫의 수를 로깅
        return pets;
    }
}
