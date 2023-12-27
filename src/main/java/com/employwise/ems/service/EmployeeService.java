package com.employwise.ems.service;

import com.employwise.ems.model.Employee;
import com.employwise.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addEmployee(Employee employee) {
        // Generate UUID and set it to employee
        employee.setId(UUID.randomUUID().toString());

        // Save the employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Send email to level 1 manager
      //  sendEmailToLevel1Manager(savedEmployee); //commented due to GMAIL SMTP Issue

        return savedEmployee.getId();
    }

    private void sendEmailToLevel1Manager(Employee employee) {
        // Assuming ReportsTo is the ID of the level 1 manager
        String level1ManagerId = employee.getReportsTo();

        if (level1ManagerId != null) {
            Employee level1Manager = employeeRepository.findById(level1ManagerId).orElse(null);

            if (level1Manager != null && level1Manager.getEmail() != null) {
                String subject = "New Employee Assignment";
                String message = String.format(
                        "%s will now work under you. Mobile number is %s and email is %s",
                        employee.getEmployeeName(), employee.getPhoneNumber(), employee.getEmail()
                );

                sendEmail(level1Manager.getEmail(), subject, message);
            }
        }
    }
    private void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        // Assuming you have configured the JavaMailSender in your application properties
        javaMailSender.send(mailMessage);
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(String id, Employee updatedEmployee) {
        updatedEmployee.setId(id);
        employeeRepository.save(updatedEmployee);
    }

    public Optional<Employee> getNthLevelManager(String employeeId, int level) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return findNthLevelManager(employee, level);
        } else {
            return Optional.empty(); // Employee with given ID not found
        }
    }

    private Optional<Employee> findNthLevelManager(Employee employee, int level) {
        if (level == 0) {
            return Optional.of(employee); // Reached the desired level
        } else if (employee.getReportsTo() != null) {
            return findNthLevelManager(employeeRepository.findById(employee.getReportsTo()).orElse(null), level - 1);
        } else {
            return Optional.empty(); // Manager not found at the specified level
        }
    }



    public Page<Employee> getAllEmployeesWithPaginationAndSorting(int page, int pageSize, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return employeeRepository.findAll(pageRequest);
    }
}
