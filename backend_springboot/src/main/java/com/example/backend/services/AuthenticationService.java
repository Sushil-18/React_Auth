package com.example.backend.services;

import com.example.backend.dto.UserDTO;
import com.example.backend.entities.UserEntity;
import com.example.backend.exceptions.UserAlreadyExistsException;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationManager authenticationManager;

    public String signup(UserDTO userDTO) throws UserAlreadyExistsException {
        String email = userDTO.getUsername();
        boolean isUserAlreadyPresent = checkIfUserAlreadyExists(email);

        if(isUserAlreadyPresent){
            throw new UserAlreadyExistsException("User with the email "+email+" is already present");
        }

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return "User has been created";
    }

    public String login(UserDTO userdto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userdto.getUsername(), userdto.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return token;
    }

    public boolean checkIfUserAlreadyExists(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }
}
