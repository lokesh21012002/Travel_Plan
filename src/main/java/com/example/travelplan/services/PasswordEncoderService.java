package com.example.travelplan.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderService {

    public PasswordEncoder encoder() ;


}
