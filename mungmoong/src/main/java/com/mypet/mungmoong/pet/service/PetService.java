package com.mypet.mungmoong.pet.service;

import java.util.List;

import com.mypet.mungmoong.pet.dto.Pet;

public interface PetService {


    void addPet(Pet pet);

    void updatePet(Pet pet);

    void deletePet(int petNo);

    Pet findPetById(int petNo);

    public List<Pet> findPetByUserId(String userId) throws Exception;
}