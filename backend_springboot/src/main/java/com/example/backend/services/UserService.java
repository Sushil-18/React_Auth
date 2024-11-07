package com.example.backend.services;

import com.example.backend.entities.UserEntity;
import com.example.backend.exceptions.ResourceNotFoundException;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserEntity getUserById(Long userId){
       return userRepository.findById(String.valueOf(userId)).orElseThrow(() -> new ResourceNotFoundException("User with "+ userId +" does not exits"));
    }
}
