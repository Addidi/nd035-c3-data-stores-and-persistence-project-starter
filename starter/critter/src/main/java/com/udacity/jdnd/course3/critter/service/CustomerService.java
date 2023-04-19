package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PetRepository petRepository;
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
    public List<Customer> getAll(){
        List<Customer> customers = customerRepository.findAll();
        for(Customer c : customers){
            List<Long> petsId = petRepository.petIdsByOwnerId(c.getId());
            if(petsId.size() > 0){
                c.setPetIds(petsId);
            }
        }
        return customers;
    }
    public Customer findById(long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        List<Long> petsId = petRepository.petIdsByOwnerId(customer.getId());
        if(petsId.size() > 0){
            customer.setPetIds(petsId);
        }
        return  customer;
    }
}
