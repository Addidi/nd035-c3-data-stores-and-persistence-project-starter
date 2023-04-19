package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        ModelMapper modelMapper = new ModelMapper();
        Pet pet = modelMapper.map(petDTO, Pet.class);
        pet = petService.save(pet);
        petDTO = modelMapper.map(pet, PetDTO.class);
        return petDTO;
    }
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findById(petId);
        ModelMapper modelMapper = new ModelMapper();
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        return  petDTO;
    }
    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getAll();
        ModelMapper modelMapper = new ModelMapper();
        List<PetDTO> petDTOS = pets.stream()
                .map(entity -> modelMapper.map(entity, PetDTO.class))
                .collect(Collectors.toList());
        return  petDTOS;
    }
    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getAll().stream().filter(n -> n.getOwnerId() == ownerId).collect(Collectors.toList());
        ModelMapper modelMapper = new ModelMapper();
        List<PetDTO> petDTOS = pets.stream()
                .map(entity -> modelMapper.map(entity, PetDTO.class))
                .collect(Collectors.toList());
        return petDTOS;
    }
}
