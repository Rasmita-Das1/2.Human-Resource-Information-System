package com.employee.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/list")
    public String listEmployees(Model model) {
        Set<Employee> employees = this.employeeService.fetchAll();
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        this.employeeService.saveEmployee(employee);
        return "redirect:/employees/list";
    }

    @RequestMapping("/delete")
    public String deleteEmployeeById(@RequestParam("id") long employeeId) {
        this.employeeService.deleteEmployeeById(employeeId);
        return "redirect:/employees/list";
    }

    @RequestMapping("/form")
    public String showForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id, Model model) {
        Employee theEmployee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", theEmployee);
        return "employee-form";
    }

    @RequestMapping("/search")
    public String searchEmployees(@RequestParam("keyword") String keyword, Model model) {
        Set<Employee> employees = employeeService.searchEmployees(keyword);
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @RequestMapping("/list-sorted")
    public String listEmployeesSorted(@RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder, Model model) {
        List<Employee> employees;

        if ("desc".equals(sortOrder)) {
            employees = employeeService.getAllEmployeesSortedByFirstNameDesc();
        } else {
            employees = employeeService.getAllEmployeesSortedByFirstNameAsc();
        }

        model.addAttribute("employees", employees);
        return "list-employees";
    }
}
