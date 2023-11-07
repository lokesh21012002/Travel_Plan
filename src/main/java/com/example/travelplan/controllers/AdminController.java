package com.example.travelplan.controllers;

import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor

public class AdminController {
    @Autowired

     private UserService userService;

    @GetMapping("/")
    public ResponseEntity  hello(){
        return ResponseEntity.ok("hello from Admin");
    }


    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize()
     ResponseEntity<List<TravelPlan>> getAllTravelPlan(){
        List<TravelPlan> allplans=userService.getAllTravelPlan();
        return ResponseEntity.status(200).body(allplans);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<TravelPlan> getTravelPlanById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.status(200).body(userService.getTravelPlanById(id));

    }

    @PostMapping("/add")

    ResponseEntity<TravelPlan> addnewTravelPlan(@RequestBody TravelPlan travelPlan) throws EntityAlreadyExistException {
        return ResponseEntity.status(200).body(userService.addnewTravelPlan(travelPlan));

    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<TravelPlan> deleteTravelPlanById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.status(200).body(userService.deleteTravelPlanById(id));

    }

    @PutMapping("/update/{id}")

    ResponseEntity<TravelPlan> updateTravelPlanById(@PathVariable Long id,@RequestBody TravelPlan travelPlan) throws EntityNotFoundException {
        return ResponseEntity.status(200).body(userService.updateTravelPlanById(id,travelPlan));


    }

    @GetMapping("/users/{id}/all")
    ResponseEntity<List<UserModel>>  getAllUsersofTravelPlan(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.status(200).body(userService.getAllUsersofTravelPlan(id));

    }






}
