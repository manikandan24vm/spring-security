package com.springsecurity.controller;

import com.springsecurity.model.EmployeeLogin;
import com.springsecurity.service.EmployeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EmployeeLoginController {
    @Autowired
    private EmployeeLoginService loginService;
    @PostMapping("/login/create")
    public ResponseEntity<String> createEmployeeReg(@RequestBody EmployeeLogin employeeLogin){
        try{
            loginService.createEmployeeLogin(employeeLogin);
            return ResponseEntity.ok("Created successfully..");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot create employee");
        }
    }

    @GetMapping("/login/get")
    public List<EmployeeLogin> getAll(){
       return loginService.getAll();
    }
}
