package com.mypet.mungmoong.pet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypet.mungmoong.pet.dto.Pet;
import com.mypet.mungmoong.pet.mapper.PetMapper;
import com.mypet.mungmoong.users.mapper.UsersMapper;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private final PetMapper petMapper;

    @Autowired
    public PetServiceImpl(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @Override
    public void addPet(Pet pet) {
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
    public List<Pet> findPetByUserId(String userId) throws Exception {
        List<Pet> petList = petMapper.findPetByUserId(userId);
        return petList;
    }
}
