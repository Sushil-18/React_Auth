package com.example.backend.controllers;

import com.example.backend.dto.UserDTO;
import com.example.backend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

   private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public UserDTO login(@RequestParam String mode, @RequestBody UserDTO logindto) throws BadRequestException {
        if(mode.equalsIgnoreCase("login")){
            return authenticationService.login(logindto);
        }
        else if(mode.equalsIgnoreCase("signup")){
            return authenticationService.signup(logindto);
        }
        else{
            throw new BadRequestException("Invalid mode:" +mode);
        }
    }

}
