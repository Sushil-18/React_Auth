package com.example.backend.services;

import com.example.backend.dto.LoginDTO;
import com.example.backend.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final ModelMapper modelMapper;
    private final LoginRepository loginRepository;

    public ResponseEntity<LoginDTO> login(LoginDTO logindto) {
    }
}
