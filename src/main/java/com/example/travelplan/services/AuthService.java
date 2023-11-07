package com.example.travelplan.services;

import com.example.travelplan.dto.LoginDTO;
import com.example.travelplan.dto.LoginResponse;
import com.example.travelplan.dto.SignupDTO;
import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;
import com.example.travelplan.models.UserModel;

public interface AuthService {

    UserModel signup(SignupDTO signupDTO) throws EntityAlreadyExistException;
    LoginResponse login(LoginDTO loginDTO) throws EntityNotFoundException;
}
