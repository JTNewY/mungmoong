package com.mypet.mungmoong.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mypet.mungmoong.pet.mapper.PetMapper;
import com.mypet.mungmoong.pet.dto.Pet;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetMapper petMapper;

    @Override
    public void insertPet(Pet pet) { // 기존 addPet 메서드를 insertPet으로 변경
        petMapper.insertPet(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        petMapper.updatePet(pet);
    }

    @Override
    public void deletePet(int petNo) {
        petMapper.deletePet(petNo);
    }

    @Override
    public Pet findPetById(int petNo) {
        return petMapper.findPetById(petNo);
    }

    @Override
    public List<Pet> findPetByUserId(String userId) {
        return petMapper.findPetsByUserId(userId);
    }
}
