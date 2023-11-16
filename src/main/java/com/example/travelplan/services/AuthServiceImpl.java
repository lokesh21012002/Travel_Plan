package com.example.travelplan.services;

import com.example.travelplan.dto.LoginDTO;
import com.example.travelplan.dto.LoginResponse;
import com.example.travelplan.dto.SignupDTO;
import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;
import com.example.travelplan.models.Role;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service

public class AuthServiceImpl implements  AuthService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private   PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;



    public LoginResponse login(LoginDTO loginDTO) throws EntityNotFoundException {
        Optional<UserModel> tmp=userRepository.findByUsername(loginDTO.getUsername());
        if(!tmp.isPresent()){
            throw new EntityNotFoundException("User Not Found Please Sign up");
        }
//        else{
//
//
//        }
        if(!passwordEncoder.matches(loginDTO.getPassword(),tmp.get().getPassword())){
            throw new EntityNotFoundException("Invalid Credentials");
        }







       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        System.out.println(authentication.isAuthenticated());
       if(!authentication.isAuthenticated()){

           throw new EntityNotFoundException("Invalid Credentials");
       }
        Optional<UserModel> userDb=userRepository.findByUsername(loginDTO.getUsername());
        if(!userDb.isPresent()){
            throw new EntityNotFoundException("Invalid credentials");
        }
//        System.out.println("Hello");

        if(!passwordEncoder.matches(loginDTO.getPassword(),userDb.get().getPassword())){
//            !userModel.getRole().equals(userDb.get().getRole())
            throw new EntityNotFoundException("Invalid Credentials");
        }

        String token= jwtService.generateToken(userDb.get());
        String refreshedToken= jwtService.refreshToken(new HashMap<>(),userDb.get());
        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refreshedToken);
        loginResponse.setStatus(HttpStatus.valueOf(200));
        loginResponse.setUserId(userDb.get().getUser_id());

        if(userDb.get().getRole().name().equals("ADMIN")){
            loginResponse.setRole("ADMIN");
        }
        else{
            loginResponse.setRole("USER");
        }

        return loginResponse;






    }



    public UserModel signup(SignupDTO signupDTO) throws EntityAlreadyExistException {
        Optional<UserModel> userDb=userRepository.findByUsername(signupDTO.getUsername());
        if(userDb.isPresent()){
            throw new EntityAlreadyExistException("User already exist");
        }
        UserModel userModel=new UserModel();
        userModel.setUsername(signupDTO.getUsername());
        userModel.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        if(signupDTO.getRole().equals("USER")){
            userModel.setRole(Role.USER);
        }
        else{
            userModel.setRole(Role.ADMIN);
        }


        return userRepository.save(userModel);
//        userModel.setRole(signupDTO.getRole().);
    }





}
