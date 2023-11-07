package com.example.travelplan.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    public String generateToken(UserDetails userDetails);
    public String refreshToken(Map<String,Object> extraClaims, UserDetails userDetails);
    public Boolean validateToken(String token, UserDetails userDetails);
    public String extractUsername(String token);
}
