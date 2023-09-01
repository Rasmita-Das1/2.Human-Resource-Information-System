package com.employee.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.employee.model.Employee;
import com.employee.model.Role;
import com.employee.model.User;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.UserRepository;

@Configuration
public class BootstrapAppData {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void loadUsers(ApplicationReadyEvent event) {

        // adding users and roles

        User kiran = new User("kiran", this.passwordEncoder.encode("kiran123"));
        User vinay = new User("vinay", this.passwordEncoder.encode("vinay123"));
        User ramesh = new User("ramesh", this.passwordEncoder.encode("ramesh123"));

        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        kiran.addRole(userRole);
        vinay.addRole(userRole);
        vinay.addRole(adminRole);
        ramesh.addRole(userRole);
        ramesh.addRole(adminRole);

        this.userRepository.save(kiran);
        this.userRepository.save(vinay);
        this.userRepository.save(ramesh);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void loadEmployees(ApplicationReadyEvent event) {

        Employee emp1 = new Employee();
        emp1.setFirstName("Johny");
        emp1.setLastName("Meir");
        emp1.setEmail("johny@gmail.com");

        Employee emp2 = new Employee();
        emp2.setFirstName("Jenny");
        emp2.setLastName("Mithow");
        emp2.setEmail("Jenny@yahoo.in");

        Employee emp3 = new Employee();
        emp3.setFirstName("Michel");
        emp3.setLastName("Socher");
        emp3.setEmail("sochermichel@hotmail.com");

        Employee emp4 = new Employee();
        emp4.setFirstName("Wilson");
        emp4.setLastName("Finao");
        emp4.setEmail("fin@co.in");

        this.employeeRepository.save(emp1);
        this.employeeRepository.save(emp2);
        this.employeeRepository.save(emp3);
        this.employeeRepository.save(emp4);
    }
}
