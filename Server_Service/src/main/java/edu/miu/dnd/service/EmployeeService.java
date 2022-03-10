package edu.miu.dnd.service;

import java.util.List;
import java.util.Optional;

import edu.miu.dnd.domain.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public Optional<Employee> findById(Long id);
	public Employee saveEmployee(Employee employee);
}
