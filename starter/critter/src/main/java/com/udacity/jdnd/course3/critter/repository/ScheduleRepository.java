package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPetIds(long petId);
    List<Schedule> findByEmployeeIds(long employeeId);

    @Query(value = "select distinct s.id, s.date " +
            " from schedule s " +
            " left join schedule_activities sa on s.id = sa.schedule_id" +
            " left join schedule_pet_ids sp on s.id = sp.schedule_id " +
            " left join pet_data p on p.id = sp.pet_ids " +
            " where p.owner_id = :customerId", nativeQuery = true)
    @Transactional
    List<Schedule> findByCustomerId(long customerId);
}
