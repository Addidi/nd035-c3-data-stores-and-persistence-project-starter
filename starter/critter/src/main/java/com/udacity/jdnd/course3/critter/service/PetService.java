package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    public Pet save(Pet pet){return petRepository.save(pet);}
    public List<Pet> getAll(){
        return petRepository.findAll();
    }
    public Pet findById(long id){ return petRepository.findById(id).orElse(null);
    }
}