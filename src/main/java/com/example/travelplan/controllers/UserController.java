package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("home/user/travel-plans/new/{id}")
    ResponseEntity<PurchaseModel> registerInNewPlanById(@PathVariable Long id, @RequestBody UserModel userModel) throws UserNotFoundException{

//            Todo

        return null;

    }

    @DeleteMapping("home/user/travel-plans/exit/{id}")
    ResponseEntity<?> exitPlanById(@PathVariable Long id,@RequestBody UserModel userModel){
//        Todo

        return null;
    }

    @GetMapping("home/user/travelplan/all/{id}")
    ResponseEntity<List<TravelPlan>>  getAllPlanByUserId(@PathVariable Long id,@RequestBody UserModel userModel){
//        Todo

        return null;
    }
















}
