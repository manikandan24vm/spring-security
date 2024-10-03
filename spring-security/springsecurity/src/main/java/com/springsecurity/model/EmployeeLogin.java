package com.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLogin {
      @Id
      private long employeeId;
      private String email;
      private String password;
      private String role;
      @OneToMany(mappedBy="empLogin",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
      @JsonManagedReference
      private Set<Authorities> authorities=new HashSet<>();
}
