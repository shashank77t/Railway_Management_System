package com.RailwayManagementSystem.RailwayManagementSystem.Model;

import com.RailwayManagementSystem.RailwayManagementSystem.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String contact;
    @Enumerated(EnumType.STRING)
    private Role role;
}
