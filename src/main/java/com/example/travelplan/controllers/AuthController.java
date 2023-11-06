package com.example.travelplan.controllers;

import com.example.travelplan.config.UserInfoUserDetailsService;
import com.example.travelplan.dto.AuthenticationDTO;
import com.example.travelplan.dto.AuthenticationResponse;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.services.UserService;
import com.example.travelplan.services.UserServiceImpl;
import com.example.travelplan.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserInfoUserDetailsService userInfoUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(authenticationDTO.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);

//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        return new AuthenticationResponse(jwt);
    }

}
