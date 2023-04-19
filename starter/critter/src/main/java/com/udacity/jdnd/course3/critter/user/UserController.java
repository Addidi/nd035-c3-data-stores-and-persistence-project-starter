package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
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
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerService.save(customer);
        customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        ModelMapper modelMapper = new ModelMapper();
        List<CustomerDTO> customerDTOS = customerService.getAll().stream().map(e -> modelMapper.map(e, CustomerDTO.class)).collect(Collectors.toList());
        return  customerDTOS;
    }
    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.findById(petId);
        ModelMapper modelMapper = new ModelMapper();
        CustomerDTO customerDTO = modelMapper.map(customerService.findById(pet.getOwnerId()), CustomerDTO.class);
        return  customerDTO;
    }
    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeService.save(employee);
        employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        return employeeDTO;
    }
    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        ModelMapper modelMapper = new ModelMapper();
        EmployeeDTO employeeDTO = modelMapper.map(employeeService.findById(employeeId), EmployeeDTO.class);
        return  employeeDTO;
    }
    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.save(employee);
    }
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        ModelMapper modelMapper = new ModelMapper();
        List<Employee> employees = employeeService.getAll().stream().filter(e -> e.getDaysAvailable().contains(employeeDTO.getDate().getDayOfWeek())
        && e.getSkills().stream().anyMatch(n -> employeeDTO.getSkills().contains(n))).collect(Collectors.toList());
        List<EmployeeDTO> employeeDTOS = employees.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
        return  employeeDTOS;
    }
}
