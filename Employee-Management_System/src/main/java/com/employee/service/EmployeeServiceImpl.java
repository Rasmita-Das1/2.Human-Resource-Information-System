package com.employee.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public Set<Employee> fetchAll() {
		return Set.copyOf(this.employeeRepository.findAll());
	}

	@Override
	public Employee findEmployeeById(long id) {
		return this.employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id"));
	}

	@Override
	public Employee updateEmployeeById(long employeeId, Employee employee) {
		Employee employeeFromRepository = this.findEmployeeById(employeeId);
		employeeFromRepository.setFirstName(employee.getFirstName());
		employeeFromRepository.setLastName(employee.getLastName());
		employeeFromRepository.setEmail(employee.getEmail());
		return this.employeeRepository.save(employeeFromRepository);
	}

	@Override
	public void deleteEmployeeById(long EmployeeId) {
		this.employeeRepository.deleteById(EmployeeId);
	}

	@Override
	public Set<Employee> searchEmployees(String keyword) {
		return employeeRepository.searchEmployees(keyword);
	}

	@Override
	public List<Employee> getAllEmployeesSortedByFirstNameAsc() {
		return employeeRepository.findByOrderByFirstNameAsc();
	}
	
	@Override
    public List<Employee> getAllEmployeesSortedByFirstNameDesc() {
        return employeeRepository.findByOrderByFirstNameDesc();
    }

}
