package edu.miu.dnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.dnd.domain.Employee;
import edu.miu.dnd.repository.EmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable(name = "id") Long employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    @GetMapping(params = "paged=true")
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
    
    @PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(name = "id") Long employeeId, @RequestBody Employee employee) {
    	employeeRepository.findById(employeeId).orElseThrow(RuntimeException::new);
		return employeeRepository.save(employee);
	}

}
