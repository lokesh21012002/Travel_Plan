package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    ResponseEntity<?> signUp(@RequestBody UserModel userModel) throws UserAlreadyExistException {


        return ResponseEntity.status(200).body(userService.signUp(userModel));
    }
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserModel userModel) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.login(userModel));


    }

    @GetMapping("home/users/travel-plans/all")
    ResponseEntity<List<TravelPlan>>   getAllTravelPlans(){
        return ResponseEntity.status(200).body(userService.getAllTravelPlan());


    }





}
