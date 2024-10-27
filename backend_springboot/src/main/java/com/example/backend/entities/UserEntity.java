package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Login_Details")
public class UserEntity {
    @Id
    @Column(unique = true)
    private String username;
    private String password;
}
