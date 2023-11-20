package com.example.travelplan.services;


import com.example.travelplan.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service

public class NodeApiService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;




    public Object callNodeApi(SignupDTO obj){

        String nodeApiUrl="http://localhost:8081/api/v1/auth/signup";
        System.out.println(obj.getUsername());
        SignupDTO signupDTO=new SignupDTO();

        signupDTO.setUsername(obj.getUsername());
        signupDTO.setRole(obj.getRole());
        System.out.println(obj.getPassword());
        signupDTO.setPassword(passwordEncoder.encode(obj.getPassword()));




        return restTemplate.postForEntity(nodeApiUrl,signupDTO,Object.class);


    }

}
