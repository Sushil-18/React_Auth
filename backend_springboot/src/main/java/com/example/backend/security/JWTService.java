package com.example.backend.security;

import com.example.backend.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JWTService {

    @Value("${jwt.secretKey}")
    private final String SECRET_KEY;

    @Value("${jwt.expirationTime}")
    private final Long EXPIRATION_TIME;

    @Value("${jwt.refresh-token.expiration}")
    private final Long REFRESH_TOKEN_EXPIRATION_TIME;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    ///generates the new access token
    public String generateToken(UserDTO userdto){
        return createToken(new HashMap<>(), userdto,EXPIRATION_TIME);
    }
    //generates the refresh token
    public String generateRefreshToken(UserDTO userdto){
        return createToken(new HashMap<>(), userdto, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    //method which creates the actual token and returns it.
    public String createToken(Map<String , Objects> extraClaims, UserDTO userdto, Long expiration){
        return Jwts.builder()
                .subject(userdto.getUsername())
                .claim("email",userdto.getUsername())
                .claim("role",userdto.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    //Extract Username from token
    public String getUserName(String token){
        extractClaim(token, Claims::getSubject);
    }

    //Extract a specific claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
      final Claims claims = extractAllClaims(token);
      return claimResolver.apply(claims);
    }

    //Extract all the claims from the token
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //check if the token is expired
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //extract expiry time fo the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //check if token is valid or not
    public boolean isTokenValid(String token, UserDTO userDTO){
        final String username = getUserName(token);
        return (username.equals(userDTO.getUsername()) && !isTokenExpired(token));
    }

}
