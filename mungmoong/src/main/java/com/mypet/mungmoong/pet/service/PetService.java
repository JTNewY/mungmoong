package com.mypet.mungmoong.pet.service;

import org.springframework.stereotype.Service;

import com.mypet.mungmoong.pet.dto.Pet;

@Service
public interface PetService {
    
    void addPet(Pet pet);

    void updatePet(Pet pet);

    void deletePet(int petNo);

    Pet findPetById(int petNo);
}