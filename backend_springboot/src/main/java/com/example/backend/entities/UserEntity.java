package com.example.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Login_Details")
public class UserEntity {
    @Id
    private String username;
    private String password;
}
