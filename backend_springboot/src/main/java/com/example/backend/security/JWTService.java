package com.example.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTService {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;


}
