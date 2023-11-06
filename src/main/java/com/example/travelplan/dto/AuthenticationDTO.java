package com.example.travelplan.dto;


import jakarta.persistence.Entity;
import lombok.*;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString




public class AuthenticationDTO {
    private String username;

    private String password;
}
