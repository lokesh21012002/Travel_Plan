package com.example.travelplan.dto;


import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String refreshToken;

}
