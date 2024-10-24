package com.example.backend.controllers;

import com.example.backend.dto.LoginDTO;
import com.example.backend.services.LoginService;
import com.example.backend.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SignUpService signUpService;

    @PostMapping("/auth")
    public LoginDTO login(@RequestParam String mode, @RequestBody LoginDTO logindto) throws BadRequestException {
        if(mode.equalsIgnoreCase("login")){
            return loginService.login(logindto);
        }
        else if(mode.equalsIgnoreCase("signup")){
            return signUpService.signup(logindto);
        }
        else{
            throw new BadRequestException("Invalid mode:" +mode);
        }
    }

}
