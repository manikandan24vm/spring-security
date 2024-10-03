package com.springsecurity.service;

import com.springsecurity.model.Employee;
import com.springsecurity.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void addEmployee(List<Employee> employee) {
            employeeRepository.saveAll(employee);
    }
    @Override

    public List<Employee> getEmployee() {
     return employeeRepository.findAll();
    }
    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).get();
    }
    @Override
    public void deleteEmployee(Long id) {
       employeeRepository.deleteById(id);
    }
}
