package com.example.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class LoginEntity {
    @Id
    private String username;
    private String password;
}
