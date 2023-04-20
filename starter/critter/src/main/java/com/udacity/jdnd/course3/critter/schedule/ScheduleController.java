package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setEmployeeIds(scheduleDTO.getEmployeeIds());
        schedule.setPetIds(scheduleDTO.getPetIds());
        schedule = scheduleService.save(schedule);
        scheduleDTO.setId(schedule.getId());
        return scheduleDTO;
    }
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<ScheduleDTO>();
        List<Schedule> schedules =  scheduleService.getAll();
        if(schedules.size() > 0){
            for(Schedule s : schedules){
                ScheduleDTO scheduleDto = new ScheduleDTO();
                scheduleDto.setActivities(s.getActivities());
                scheduleDto.setDate(s.getDate());
                scheduleDto.setId(s.getId());
                scheduleDto.setEmployeeIds(new ArrayList<>(s.getEmployeeIds()));
                scheduleDto.setPetIds(new ArrayList<>(s.getPetIds()));
                scheduleDTOS.add(scheduleDto);
            }
        }
        return  scheduleDTOS;
    }
    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<ScheduleDTO>();
        if(schedules.size() > 0){
            for(Schedule s : schedules){
                ScheduleDTO scheduleDto = new ScheduleDTO();
                scheduleDto.setActivities(s.getActivities());
                scheduleDto.setDate(s.getDate());
                scheduleDto.setId(s.getId());
                scheduleDto.setEmployeeIds(s.getEmployeeIds());
                scheduleDto.setPetIds(s.getPetIds());
                scheduleDTOS.add(scheduleDto);
            }
        }
        return scheduleDTOS;
    }
    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<ScheduleDTO>();
        if(schedules.size() > 0){
            for(Schedule s : schedules){
                ScheduleDTO scheduleDto = new ScheduleDTO();
                scheduleDto.setActivities(s.getActivities());
                scheduleDto.setDate(s.getDate());
                scheduleDto.setId(s.getId());
                scheduleDto.setEmployeeIds(s.getEmployeeIds());
                scheduleDto.setPetIds(s.getPetIds());
                scheduleDTOS.add(scheduleDto);
            }
        }
        return  scheduleDTOS;
    }
    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<ScheduleDTO>();
        if(schedules.size() > 0){
            for(Schedule s : schedules){
                ScheduleDTO scheduleDto = new ScheduleDTO();
                scheduleDto.setActivities(s.getActivities());
                scheduleDto.setDate(s.getDate());
                scheduleDto.setId(s.getId());
                scheduleDto.setEmployeeIds(s.getEmployeeIds());
                scheduleDto.setPetIds(s.getPetIds());
                scheduleDTOS.add(scheduleDto);
            }
        }
        return  scheduleDTOS;
    }
}
