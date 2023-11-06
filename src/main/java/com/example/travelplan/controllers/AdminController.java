package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.repository.TravelPlanRespository;
import com.example.travelplan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/home/travel-plan")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    @Autowired

     private UserService userService;


    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize()
     ResponseEntity<List<TravelPlan>> getAllTravelPlan(){
        List<TravelPlan> allplans=userService.getAllTravelPlan();
        return ResponseEntity.status(200).body(allplans);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<TravelPlan> getTravelPlanById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.getTravelPlanById(id));

    }

    @PostMapping("/add")

    ResponseEntity<TravelPlan> addnewTravelPlan(@RequestBody TravelPlan travelPlan) throws UserAlreadyExistException {
        return ResponseEntity.status(200).body(userService.addnewTravelPlan(travelPlan));

    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<TravelPlan> deleteTravelPlanById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.deleteTravelPlanById(id));

    }

    @PutMapping("/update/{id}")

    ResponseEntity<TravelPlan> updateTravelPlanById(@PathVariable Long id,@RequestBody TravelPlan travelPlan) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.updateTravelPlanById(id,travelPlan));


    }

    @GetMapping("/users/{id}")
    ResponseEntity<List<UserModel>>  getAllUsersofTravelPlan(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.status(200).body(userService.getAllUsersofTravelPlan(id));

    }






}
