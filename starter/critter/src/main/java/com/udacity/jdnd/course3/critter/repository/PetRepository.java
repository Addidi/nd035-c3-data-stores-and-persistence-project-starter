package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "select id from pet_data where owner_id = :ownerId", nativeQuery = true)
    @Transactional
    List<Long> petIdsByOwnerId(long ownerId);
}
