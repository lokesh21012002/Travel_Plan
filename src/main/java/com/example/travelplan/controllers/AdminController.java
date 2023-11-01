package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.repository.TravelPlanRespository;
import com.example.travelplan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
     private UserService userService;


    @GetMapping("/admin/home/travel-plan/all")
     ResponseEntity<List<TravelPlan>> getAllTravelPlan(){
        List<TravelPlan> allplans=userService.getAllTravelPlan();
        return ResponseEntity.status(200).body(allplans);
    }

    @GetMapping("/admin/home/travel-plan/{id}")
    ResponseEntity<TravelPlan> getTravelPlanById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.getTravelPlanById(id));

    }

    @PostMapping("/admin/home/travel-plan/add")
    ResponseEntity<TravelPlan> addnewTravelPlan(@RequestBody TravelPlan travelPlan) throws UserAlreadyExistException {
        return ResponseEntity.status(200).body(userService.addnewTravelPlan(travelPlan));

    }

    @DeleteMapping("/admin/home/travel-plan/delete/{id}")
    ResponseEntity<TravelPlan> deleteTravelPlanById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.deleteTravelPlanById(id));

    }

    @PutMapping("/admin/home/travel-plan/update/{id}")

    ResponseEntity<TravelPlan> updateTravelPlanById(@PathVariable Long id,@RequestBody TravelPlan travelPlan) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.updateTravelPlanById(id,travelPlan));


    }

    @GetMapping("admin/home/users/travel-plan/{id}")
    ResponseEntity<List<UserModel>>  getAllUsersofTravelPlan(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.getAllUsersofTravelPlan(id));

    }






}
