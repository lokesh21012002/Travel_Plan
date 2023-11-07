package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;

import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

//@PreAuthorize("ha")


public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public ResponseEntity<UserModel>  hello(){
//
//        UserModel d=new UserModel();
//        d.setRole(Role.USER);
//        d.setUsername("aa");
//        d.setPassword("ww");
//        d.setUser_id(Long.valueOf(1));
//
//        return ResponseEntity.ok(d);
//    }


//    @PostMapping("/signup")
//    ResponseEntity<?> signUp(@RequestBody UserModel userModel) throws UserAlreadyExistException {
//
//
//        return ResponseEntity.status(200).body(userService.signUp(userModel));
//    }
//    @PostMapping("/login")
//    ResponseEntity<?> login(@RequestBody UserModel userModel) throws UserNotFoundException {
//        return ResponseEntity.status(200).body(userService.login(userModel));
//
//
//    }

    @GetMapping("/travel-plans/all")
    ResponseEntity<List<TravelPlan>>   getAllTravelPlans(){
        return ResponseEntity.status(200).body(userService.getAllTravelPlan());


    }

    @PostMapping("/add/{user_id}/travel-plans/{travel_id}")
    ResponseEntity<?> registerInNewPlan( @PathVariable Long user_id,@PathVariable Long travel_id) throws EntityNotFoundException, EntityAlreadyExistException {
        return ResponseEntity.status(200).body(userService.registerInNewPlan(user_id,travel_id));





    }

    @DeleteMapping("/exit/{user_id}/travel-plans/{travel_id}")
    ResponseEntity<PurchaseModel> exitFromPlan(@PathVariable Long user_id, @PathVariable Long travel_id) throws EntityNotFoundException {

        return ResponseEntity.status(200).body(userService.exitFromPlan(user_id,travel_id));




    }

    @GetMapping("/travelplan/all/{id}")
    ResponseEntity<List<TravelPlan>>  getAllPlanByUserId(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.status(200).body(userService.getAllPlanByUserId(id));



//        return ResponseEntity.status(200).body(userService.getAllPlanByUserId(id));






    }
















}
