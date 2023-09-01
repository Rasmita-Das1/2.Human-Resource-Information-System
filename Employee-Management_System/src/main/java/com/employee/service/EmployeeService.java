package com.employee.service;

import java.util.List;
import java.util.Set;

import com.employee.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	Set<Employee> fetchAll();

	Employee findEmployeeById(long id);

	Employee updateEmployeeById(long employeeId, Employee employee);

	void deleteEmployeeById(long EmployeeId);

	Set<Employee> searchEmployees(String keyword);

	List<Employee> getAllEmployeesSortedByFirstNameAsc();

	List<Employee> getAllEmployeesSortedByFirstNameDesc();

}
