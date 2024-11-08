package com.example.backend.security;

import com.example.backend.dto.UserDTO;
import com.example.backend.entities.UserEntity;
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
    private String SECRET_KEY;

    @Value("${jwt.expirationTime}")
    private Long EXPIRATION_TIME;

    @Value("${jwt.refresh-token.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION_TIME;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    ///generates the new access token
    public String generateToken(UserEntity userEntity){
        return createToken(new HashMap<>(), userEntity,EXPIRATION_TIME);
    }
    //generates the refresh token
    public String generateRefreshToken(UserEntity userEntity){
        return createToken(new HashMap<>(), userEntity, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    //method which creates the actual token and returns it.
    public String createToken(Map<String , Objects> extraClaims, UserEntity userEntity, Long expiration){
        return Jwts.builder()
                .subject(userEntity.getUsername())
                .claim("userId",userEntity.getId())
                .claim("email",userEntity.getUsername())
                .claim("role",userEntity.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    //Extract Username from token
    public String getUserName(String token){
      return extractClaim(token, Claims::getSubject);
    }

    //Extract User ID from token
    public long getUserId(String token){
       return Long.parseLong(extractClaim(token,Claims::getId)) ;
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
    public boolean isTokenValid(String token, UserEntity userEntity){
        final String username = getUserName(token);
        return (username.equals(userEntity.getUsername()) && !isTokenExpired(token));
    }

}
