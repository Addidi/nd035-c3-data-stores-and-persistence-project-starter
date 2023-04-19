package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }
    public Employee findById(long id){
        return employeeRepository.findById(id).orElse(null);
    }
}