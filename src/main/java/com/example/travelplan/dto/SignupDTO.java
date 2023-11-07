package com.example.travelplan.dto;


import com.example.travelplan.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDTO {
    private String username;
    private String password;
    private String role;

}
