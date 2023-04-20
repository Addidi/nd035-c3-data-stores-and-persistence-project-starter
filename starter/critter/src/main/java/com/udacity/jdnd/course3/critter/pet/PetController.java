package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = new Pet(petDTO.getId(), petDTO.getType().toString(), petDTO.getName(),petDTO.getOwnerId(), petDTO.getBirthDate(), petDTO.getNotes());
        Pet newPet = petService.save(pet);
        PetDTO newPetDto = new PetDTO();
        newPetDto.setId(newPet.getId());
        newPetDto.setName(newPet.getName());
        newPetDto.setType(petDTO.getType());
        newPetDto.setNotes(newPet.getNotes());
        newPetDto.setOwnerId(newPet.getOwnerId());
        newPetDto.setBirthDate(newPet.getBirthDate());
        return newPetDto;
    }
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findById(petId);
        PetDTO petDTO = new PetDTO();
        if(pet != null){
            petDTO.setBirthDate(pet.getBirthDate());
            petDTO.setId(pet.getId());
            petDTO.setName(pet.getName());
            petDTO.setNotes(pet.getNotes());
            petDTO.setType(PetType.valueOf(pet.getType()));
            petDTO.setOwnerId(pet.getOwnerId());
        }
        return petDTO;
    }
    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOS = new ArrayList<PetDTO>();
        List<Pet> pets = petService.getAll();
        if(pets.size() > 0){
            for (Pet pet : pets) {
                PetDTO petDTO = new PetDTO();
                petDTO.setBirthDate(pet.getBirthDate());
                petDTO.setId(pet.getId());
                petDTO.setName(pet.getName());
                petDTO.setNotes(pet.getNotes());
                petDTO.setType(PetType.valueOf(pet.getType()));
                petDTO.setOwnerId(pet.getOwnerId());
                petDTOS.add(petDTO);
            }
        }
        return  petDTOS;
    }
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getAll().stream().filter(n -> n.getOwnerId() == ownerId).collect(Collectors.toList());
        List<PetDTO> petDTOS = new ArrayList<PetDTO>();
        if(pets.size() > 0){
            for (Pet pet : pets) {
                PetDTO petDTO = new PetDTO();
                petDTO.setBirthDate(pet.getBirthDate());
                petDTO.setId(pet.getId());
                petDTO.setName(pet.getName());
                petDTO.setNotes(pet.getNotes());
                petDTO.setType(PetType.valueOf(pet.getType()));
                petDTO.setOwnerId(pet.getOwnerId());
                petDTOS.add(petDTO);
            }
        }
        return petDTOS;
    }
}
