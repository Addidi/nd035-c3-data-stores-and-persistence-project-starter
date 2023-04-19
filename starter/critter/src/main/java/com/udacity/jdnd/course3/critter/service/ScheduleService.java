package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    public Schedule save(Schedule schedule){
        return scheduleRepository.save(schedule);
    }
    public List<Schedule> getAll(){
        return scheduleRepository.findAll();
    }
    public Schedule findById(long id){ return scheduleRepository.findById(id).orElse(null); }
    public List<Schedule> getScheduleForPet(long petId) { return scheduleRepository.findByPetIds(petId);}
    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findByEmployeeIds(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        return scheduleRepository.findByCustomerId(customerId);
    }
}
