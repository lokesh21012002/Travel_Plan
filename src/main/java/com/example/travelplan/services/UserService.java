package com.example.travelplan.services;

import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;
//import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {
    UserDetailsService userDetailsService();

    UserModel signUp(UserModel userModel) throws EntityAlreadyExistException;

    UserModel login(UserModel userModel) throws EntityNotFoundException;

    List<TravelPlan> getAllTravelPlan();

    TravelPlan getTravelPlanById(Long id) throws EntityNotFoundException;

    TravelPlan addnewTravelPlan(TravelPlan travelPlan) throws EntityAlreadyExistException;

    TravelPlan deleteTravelPlanById(Long id) throws EntityNotFoundException;

    TravelPlan updateTravelPlanById(Long id, TravelPlan travelPlan) throws EntityNotFoundException;

//    List<UserModel> getAllUsersofTravelPlan(Long id) throws UserNotFoundException;

    PurchaseModel registerInNewPlan(Long user_id, Long travel_id) throws EntityNotFoundException, EntityAlreadyExistException;

    PurchaseModel exitFromPlan(Long user_id,Long travel_id) throws EntityNotFoundException;

    List<TravelPlan> getAllPlanByUserId(Long id) throws EntityNotFoundException;

    List<UserModel> getAllUsersofTravelPlan(Long id) throws EntityNotFoundException;
}
