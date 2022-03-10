package edu.miu.dnd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.dnd.domain.Employee;
import edu.miu.dnd.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
}
