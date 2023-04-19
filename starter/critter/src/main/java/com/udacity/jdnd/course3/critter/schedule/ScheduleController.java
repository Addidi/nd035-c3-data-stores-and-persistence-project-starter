package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        ModelMapper modelMapper = new ModelMapper();
        Schedule schedule = modelMapper.map(scheduleDTO, Schedule.class);
        schedule = scheduleService.save(schedule);
        scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        return  scheduleDTO;
    }
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        ModelMapper modelMapper = new ModelMapper();
        List<ScheduleDTO> scheduleDTOS = scheduleService.getAll().stream().map(e -> modelMapper.map(e,ScheduleDTO.class)).collect(Collectors.toList());
        return  scheduleDTOS;
    }
    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        ModelMapper modelMapper = new ModelMapper();
        List<ScheduleDTO> scheduleDTOS = scheduleService.getScheduleForPet(petId).stream().map(e ->modelMapper.map(e, ScheduleDTO.class)).collect(Collectors.toList());
        return scheduleDTOS;
    }
    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        ModelMapper modelMapper = new ModelMapper();
        List<ScheduleDTO> scheduleDTOS = scheduleService.getScheduleForEmployee(employeeId).stream().map(
                e -> modelMapper.map(e, ScheduleDTO.class)
        ).collect(Collectors.toList());
        return  scheduleDTOS;
    }
    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        ModelMapper modelMapper = new ModelMapper();
        List<ScheduleDTO> scheduleDTOS = scheduleService.getScheduleForCustomer(customerId).stream().map(
                e -> modelMapper.map(e, ScheduleDTO.class)
        ).collect(Collectors.toList());
        return  scheduleDTOS;
    }
}
