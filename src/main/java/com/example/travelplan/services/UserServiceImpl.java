package com.example.travelplan.services;

import com.example.travelplan.exceptions.UserAlreadyExistException;
import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.repository.TravelPlanRespository;
import com.example.travelplan.repository.UserRepository;
import com.example.travelplan.repository.UserTravelPlanRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Lazy
    private PasswordEncoderService passwordEncoderService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TravelPlanRespository travelPlanRespository;


    @Autowired
    private UserTravelPlanRepository userTravelPlanRepository;





    @Override
    public UserModel signUp(UserModel userModel) throws UserAlreadyExistException {
        Optional<UserModel> userDb=userRepository.findByUsername(userModel.getUsername());

        if(userDb.isPresent()){
            throw new UserAlreadyExistException("User Already Exist");


        }
        UserModel user=new UserModel();
        user.setPassword(passwordEncoderService.encoder().encode(userModel.getPassword()));

        user.setRole(userModel.getRole());
        user.setUsername(userModel.getUsername());



        return userRepository.save(user);
    }

    @Override
    public UserModel login(UserModel userModel) throws UserNotFoundException {



        Optional<UserModel> userDb=userRepository.findByUsername(userModel.getUsername());



        if(!userDb.isPresent()){
            throw new UserNotFoundException("User Not found");

        }

//        String reqPass=passwordEncoderService.encoder().encode(userModel.getPassword());
//        String dbPass=passwordEncoderService.encoder()
//
//        System.out.println(reqPass);
//        System.out.println(dbPass);
//        UserModel demo=new UserModel(Long.valueOf(1),reqPass,dbPass,"Admin");


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(userModel.getPassword(),userDb.get().getPassword())){
//            !userModel.getRole().equals(userDb.get().getRole())
            throw new UserNotFoundException("Invalid Credentials");
        }




        return userDb.get();



//        return null;
    }

    @Override
    public List<TravelPlan> getAllTravelPlan() {
        return  travelPlanRespository.findAll();

    }

    @Override
    public TravelPlan getTravelPlanById(Long id) throws UserNotFoundException {

        Optional<TravelPlan> travelPlan=travelPlanRespository.findById(id);
        if(!travelPlan.isPresent()){
            throw new UserNotFoundException("Travel Plan does't exist");

        }

        return travelPlan.get();


    }

    @Override
    public TravelPlan addnewTravelPlan(TravelPlan travelPlan) throws UserAlreadyExistException {
        Optional<TravelPlan> planDb=travelPlanRespository.findTravelPlanByName(travelPlan.getName());
        if(planDb.isPresent()){
            throw new UserAlreadyExistException("Travel plan already existed");

        }

        return travelPlanRespository.save(travelPlan);


    }

    @Override
    public TravelPlan updateTravelPlanById(Long id, TravelPlan travelPlan) throws UserNotFoundException {
        Optional<TravelPlan> travelPlannDb=travelPlanRespository.findById(id);
        if(!travelPlannDb.isPresent()){
            throw new UserNotFoundException("Travel Plan Does't exist");
        }

        if(Objects.nonNull(travelPlan.getName()) && !"".equalsIgnoreCase(travelPlan.getName())){
            travelPlannDb.get().setName(travelPlan.getName());

        }
        if(Objects.nonNull(travelPlan.getDestination()) && !"".equalsIgnoreCase(travelPlan.getDescription())){
            travelPlannDb.get().setDescription(travelPlan.getDescription());

        }
        if(Objects.nonNull(travelPlan.getDescription()) && !"".equalsIgnoreCase(travelPlan.getDescription())){
            travelPlannDb.get().setDestination(travelPlan.getDestination());

        }

        if(Objects.nonNull(travelPlan.getDate())){
            travelPlannDb.get().setDate(travelPlan.getDate());

        }

        travelPlanRespository.save(travelPlan);
        return travelPlannDb.get();





    }

    @Override
    public List<UserModel> getAllUsersofTravelPlan(Long id) throws UserNotFoundException {
        Optional<TravelPlan> travelDb=travelPlanRespository.findById(id);
        if(!travelDb.isPresent()){
            throw new UserNotFoundException("Travel Plan Not Exist");

        }
        return userTravelPlanRepository.findByPlanId(id);

    }

    @Override
    public TravelPlan deleteTravelPlanById(Long id) throws UserNotFoundException {

        Optional<TravelPlan> travelPlanDb=travelPlanRespository.findById(id);
        if(!travelPlanDb.isPresent()){
            throw new UserNotFoundException("Travel plan does't exist");

        }

        travelPlanRespository.deleteById(id);
        return travelPlanDb.get();


    }
}
