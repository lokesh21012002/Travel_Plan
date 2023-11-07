package com.example.travelplan.dto;


import jakarta.persistence.Entity;
import jdk.jfr.DataAmount;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor





public class AuthenticationDTO {
    private String username;

    private String password;
}
