package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PetService petService;
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customer.getName());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setNotes(customer.getNotes());
        customer.setPetIds(customerDTO.getPetIds());
        customer = customerService.save(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;
    }
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getAll();
        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>();
        if(customers.size() > 0){
            for(Customer c : customers){
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(c.getId());
                customerDTO.setName(c.getName());
                customerDTO.setNotes(c.getNotes());
                customerDTO.setPetIds(c.getPetIds());
                customerDTO.setPhoneNumber(c.getPhoneNumber());
                customerDTOS.add(customerDTO);
            }
        }
        return  customerDTOS;
    }
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.findById(petId);
        Customer c = customerService.findById(pet.getOwnerId());
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(c.getId());
        customerDTO.setName(c.getName());
        customerDTO.setNotes(c.getNotes());
        customerDTO.setPetIds(c.getPetIds());
        customerDTO.setPhoneNumber(c.getPhoneNumber());
        return  customerDTO;
    }
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee = employeeService.save(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }
    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSkills(employee.getSkills());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        return  employeeDTO;
    }
    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
       if(employee != null){
           employee.setDaysAvailable(daysAvailable);
           employeeService.save(employee);
       }
    }
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getAll().stream().filter(e -> e.getDaysAvailable().contains(employeeDTO.getDate().getDayOfWeek())
        && e.getSkills().stream().anyMatch(n -> employeeDTO.getSkills().contains(n))).collect(Collectors.toList());
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();
        if(employees.size() > 0){
            for(Employee employee : employees){
                EmployeeDTO empDTO = new EmployeeDTO();
                empDTO.setId(employee.getId());
                empDTO.setName(employee.getName());
                empDTO.setSkills(employee.getSkills());
                empDTO.setDaysAvailable(employee.getDaysAvailable());
                employeeDTOS.add(empDTO);
            }
        }
        return  employeeDTOS;
    }
}
