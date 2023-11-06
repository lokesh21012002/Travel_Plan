package com.example.travelplan.services;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
//import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.ErrorMesage;
import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserService {

    UserModel signUp(UserModel userModel) throws UserAlreadyExistException;

    UserModel login(UserModel userModel) throws UserNotFoundException;

    List<TravelPlan> getAllTravelPlan();

    TravelPlan getTravelPlanById(Long id) throws UserNotFoundException;

    TravelPlan addnewTravelPlan(TravelPlan travelPlan) throws UserAlreadyExistException;

    TravelPlan deleteTravelPlanById(Long id) throws UserNotFoundException;

    TravelPlan updateTravelPlanById(Long id, TravelPlan travelPlan) throws UserNotFoundException;

//    List<UserModel> getAllUsersofTravelPlan(Long id) throws UserNotFoundException;

    PurchaseModel registerInNewPlan(Long user_id, Long travel_id) throws UserNotFoundException, UserAlreadyExistException;

    PurchaseModel exitFromPlan(Long user_id,Long travel_id) throws UserNotFoundException;

    List<TravelPlan> getAllPlanByUserId(Long id) throws UserNotFoundException;

    List<UserModel> getAllUsersofTravelPlan(Long id) throws UserNotFoundException;
}
