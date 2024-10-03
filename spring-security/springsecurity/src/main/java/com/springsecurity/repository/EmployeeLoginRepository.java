package com.springsecurity.repository;

import com.springsecurity.model.EmployeeLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeLoginRepository extends JpaRepository<EmployeeLogin,Long> {
    @Query("select el from EmployeeLogin el where el.email= :username")
    Optional<EmployeeLogin> findByEmail(@Param("username") String username);
}
