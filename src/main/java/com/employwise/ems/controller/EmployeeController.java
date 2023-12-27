package com.employwise.ems.controller;

import com.employwise.ems.model.Employee;
import com.employwise.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/{employeeId}/manager/{level}")
    public Optional<Employee> getNthLevelManager(@PathVariable String employeeId, @PathVariable int level) {
        return employeeService.getNthLevelManager(employeeId, level);
    }

    @GetMapping("/paged")
    public Page<Employee> getAllEmployeesWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "employeeName") String sortBy
    ) {
        return employeeService.getAllEmployeesWithPaginationAndSorting(page, pageSize, sortBy);
    }

}
