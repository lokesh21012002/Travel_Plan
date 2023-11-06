package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;

import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@PreAuthorize("hasAuthority('ROLE_USER')")


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

    @PostMapping("home/user/{user_id}/travel-plans/{travel_id}/new/")
    ResponseEntity<?> registerInNewPlan( @PathVariable Long user_id,@PathVariable Long travel_id) throws UserNotFoundException, UserAlreadyExistException {
        return ResponseEntity.status(200).body(userService.registerInNewPlan(user_id,travel_id));





    }

    @DeleteMapping("home/user/{user_id}/travel-plans/{travel_id}/exit")
    ResponseEntity<PurchaseModel> exitFromPlan(@PathVariable Long user_id, @PathVariable Long travel_id) throws UserNotFoundException {

        return ResponseEntity.status(200).body(userService.exitFromPlan(user_id,travel_id));




    }

    @GetMapping("home/user/travelplan/all/{id}")
    ResponseEntity<List<TravelPlan>>  getAllPlanByUserId(@PathVariable Long id) throws UserNotFoundException {

        return ResponseEntity.status(200).body(userService.getAllPlanByUserId(id));



//        return ResponseEntity.status(200).body(userService.getAllPlanByUserId(id));






    }
















}
