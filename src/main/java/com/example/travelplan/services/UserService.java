package com.example.travelplan.services;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;

import java.util.List;

public interface UserService {

    UserModel signUp(UserModel userModel) throws UserAlreadyExistException;

    UserModel login(UserModel userModel) throws UserNotFoundException;

    List<TravelPlan> getAllTravelPlan();

    TravelPlan getTravelPlanById(Long id) throws UserNotFoundException;

    TravelPlan addnewTravelPlan(TravelPlan travelPlan) throws UserAlreadyExistException;

    TravelPlan deleteTravelPlanById(Long id) throws UserNotFoundException;

    TravelPlan updateTravelPlanById(Long id, TravelPlan travelPlan) throws UserNotFoundException;

    List<UserModel> getAllUsersofTravelPlan(Long id) throws UserNotFoundException;

}
