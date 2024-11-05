package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Username cannot be empty") @Email(message = "Username must be valid email")
    private String username;
    @NotBlank(message = "Password cannot be empty") @Size(min = 8, message = "password length should be more than 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;
    @Value("ADMIN")
    private String role;

   /* @Override
    public Collection<? extends GrantedAuthority> getAuthority(){
        return List.of(new SimpleGrantedAuthority(role));
    }*/
}
