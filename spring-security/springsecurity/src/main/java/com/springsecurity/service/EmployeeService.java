package com.springsecurity.service;

import com.springsecurity.model.Employee;
import com.springsecurity.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(List<Employee> employee);
    public List<Employee> getEmployee();

    public Employee getById(Long id);
    public void deleteEmployee(Long id);
}
