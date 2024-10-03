package com.springsecurity.service;

import com.springsecurity.model.Employee;
import com.springsecurity.model.EmployeeLogin;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface EmployeeLoginService extends UserDetailsService {


    void createEmployeeLogin(EmployeeLogin employeeLogin);

    List<EmployeeLogin> getAll();
}
