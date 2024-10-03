package com.springsecurity.service;

import com.springsecurity.model.Authorities;
import com.springsecurity.model.Employee;
import com.springsecurity.model.EmployeeLogin;
import com.springsecurity.repository.EmployeeLoginRepository;
import com.springsecurity.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeLoginServiceImpl implements EmployeeLoginService{
    @Autowired
    private EmployeeLoginRepository loginRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createEmployeeLogin(EmployeeLogin employeeLogin) {
        String password=employeeLogin.getPassword();
        employeeLogin.setPassword(passwordEncoder.encode(password));
        loginRepository.save(employeeLogin);
    }

    @Override
    public List<EmployeeLogin> getAll() {
        return loginRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeLogin credentials=loginRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user not found with email :"+username));
       List<GrantedAuthority> authorities=credentials.getAuthorities().stream().map(authority-> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList());
        //List<GrantedAuthority> authorities= List.of(new SimpleGrantedAuthority(credentials.getRole()));
        return new User(credentials.getEmail(),credentials.getPassword(),authorities);
    }

}
