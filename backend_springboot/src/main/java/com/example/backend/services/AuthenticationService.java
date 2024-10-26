package com.example.backend.services;

import com.example.backend.dto.UserDTO;
import com.example.backend.entities.UserEntity;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.backend.security.JWTService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO signup(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserDTO login(UserDTO userdto) {
        UserEntity userEntity = modelMapper.map(userdto, UserEntity.class);

        return modelMapper.map(userEntity, UserDTO.class);
    }
}
