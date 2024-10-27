package com.example.backend.services;

import com.example.backend.dto.UserDTO;
import com.example.backend.entities.UserEntity;
import com.example.backend.exceptions.UserAlreadyExistsException;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.backend.security.JWTService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO signup(UserDTO userDTO) throws UserAlreadyExistsException {
        String email = userDTO.getUsername();
        boolean isUserAlreadyPresent = checkIfUserAlreadyExists(email);

        if(isUserAlreadyPresent){
            throw new UserAlreadyExistsException("User with the email "+email+" is already present");
        }

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return modelMapper.map(userEntity, UserDTO.class);
    }

    public UserDTO login(UserDTO userdto) {
        UserEntity userEntity = modelMapper.map(userdto, UserEntity.class);

        return modelMapper.map(userEntity, UserDTO.class);
    }

    public boolean checkIfUserAlreadyExists(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }
}
