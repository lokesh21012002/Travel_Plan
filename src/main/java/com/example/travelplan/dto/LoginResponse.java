package com.example.travelplan.dto;


import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LoginResponse {

    private HttpStatus status;
    private Long userId;



    private String token;
    private String refreshToken;

    private String role;


}
