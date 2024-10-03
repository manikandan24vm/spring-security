package com.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //private long employeeLoginId;
    private String authority;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="employee_id")
    @JsonBackReference
    private EmployeeLogin empLogin;

}
