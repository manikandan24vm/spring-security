package com.springsecurity.controller;

import com.springsecurity.model.Employee;
import com.springsecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;


    @PostMapping("/employee/addEmp")
    public ResponseEntity<String> addEmployee(@RequestBody List<Employee> employee) {
        try {
            service.addEmployee(employee);
            return ResponseEntity.ok("Employee Added Successfully..");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @GetMapping("/employee/getAll")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        if(!service.getEmployee().isEmpty()){
            return ResponseEntity.ok(service.getEmployee());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

   @GetMapping("/employee/get/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        if(!service.getEmployee().isEmpty()){
            return ResponseEntity.ok(service.getById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteEmployee(id);
            return ResponseEntity.ok("Employee with ID : "+id+" Successfully..");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
