package com.example.travelplan.dto;


import com.example.travelplan.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class SignupDTO {
    private String username;
    private String password;
    private String role;

}
