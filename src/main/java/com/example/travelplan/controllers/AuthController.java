package com.example.travelplan.controllers;

import com.example.travelplan.dto.LoginDTO;
import com.example.travelplan.dto.LoginResponse;
import com.example.travelplan.dto.SignupDTO;
import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.services.AuthService;
import com.example.travelplan.services.NodeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController


    

@RequestMapping("/api/v1/auth")


public class AuthController {



    @Autowired

    private  AuthService authService;


    @Autowired
    private NodeApiService nodeApiService;



    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) throws EntityAlreadyExistException {
        return ResponseEntity.ok(authService.signup(signupDTO));



//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    UserInfoUserDetailsService userInfoUserDetailsService;
//
//
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//
//
//
//    @PostMapping("/")
//    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));
////             authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Incorrect username or password!");
//        } catch (DisabledException disabledException) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
//            return null;
//        }
////
//        System.out.println(authenticationDTO.getUsername());
//        System.out.println(authenticationDTO.getPassword());
//        final UserDetails userDetails = userInfoUserDetailsService.loadUserByUsername(authenticationDTO.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        return new AuthenticationResponse(jwt);

//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getUsername());
//
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        return new AuthenticationResponse(jwt);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) throws EntityNotFoundException {
        return ResponseEntity.ok(authService.login(loginDTO));
    }


}
