package com.example.travelplan.services;

import com.example.travelplan.exceptions.EntityAlreadyExistException;
import com.example.travelplan.exceptions.EntityNotFoundException;
//import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
//import com.example.travelplan.repository.PurchaseModelRepository;
import com.example.travelplan.repository.TravelPlanRespository;
import com.example.travelplan.repository.UserRepository;
//import com.example.travelplan.repository.UserTravelPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    @Lazy
    private PasswordEncoderService passwordEncoderService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TravelPlanRespository travelPlanRespository;


//    @Autowired
//    private UserTravelPlanRepository userTravelPlanRepository;

//    @Autowired
//    PurchaseModelRepository purchaseModelRepository;



@Override
public UserDetailsService userDetailsService(){
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if(!userRepository.findByUsername(username).isPresent()){

                try {
                    throw  new EntityNotFoundException("User not found");
                } catch (EntityNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

            return  userRepository.findFirstByUsername(username);


        }
    };
}


    @Override
    public UserModel signUp(UserModel userModel) throws EntityAlreadyExistException {
        Optional<UserModel> userDb=userRepository.findByUsername(userModel.getUsername());

        if(userDb.isPresent()){
            throw new EntityAlreadyExistException("User Already Exist");


        }
        UserModel user=new UserModel();
        user.setPassword(passwordEncoderService.encoder().encode(userModel.getPassword()));

        user.setRole(userModel.getRole());
        user.setUsername(userModel.getUsername());



        return userRepository.save(user);
    }

    @Override
    public UserModel login(UserModel userModel) throws EntityNotFoundException {



        Optional<UserModel> userDb=userRepository.findByUsername(userModel.getUsername());



        if(!userDb.isPresent()){
            throw new EntityNotFoundException("User Not found");

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
            throw new EntityNotFoundException("Invalid Credentials");
        }




        return userDb.get();



//        return null;
    }

    @Override
    public List<TravelPlan> getAllTravelPlan() {
        return  travelPlanRespository.findAll();

    }

    @Override
    public TravelPlan getTravelPlanById(Long id) throws EntityNotFoundException {

        Optional<TravelPlan> travelPlan=travelPlanRespository.findById(id);
        if(!travelPlan.isPresent()){
            throw new EntityNotFoundException("Travel Plan does't exist");

        }

        return travelPlan.get();


    }

    @Override
    public TravelPlan addnewTravelPlan(TravelPlan travelPlan) throws EntityAlreadyExistException {
        Optional<TravelPlan> planDb=travelPlanRespository.findTravelPlanByName(travelPlan.getName());
        if(planDb.isPresent()){
            throw new EntityAlreadyExistException("Travel plan already existed");

        }

        return travelPlanRespository.save(travelPlan);


    }

    @Override
    public TravelPlan updateTravelPlanById(Long id, TravelPlan travelPlan) throws EntityNotFoundException {
        Optional<TravelPlan> travelPlannDb=travelPlanRespository.findById(id);
        if(!travelPlannDb.isPresent()){
            throw new EntityNotFoundException("Travel Plan Does't exist");
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

        travelPlanRespository.save(travelPlannDb.get());
        return travelPlannDb.get();





    }

//    @Override
//    public List<UserModel> getAllUsersofTravelPlan(Long id) throws UserNotFoundException {
//        Optional<TravelPlan> travelDb=travelPlanRespository.findById(id);
//        if(!travelDb.isPresent()){
//            throw new UserNotFoundException("Travel Plan Not Exist");
//
//        }
//        return userTravelPlanRepository.findByPlanId(id);
//
//    }

    @Override
    public PurchaseModel registerInNewPlan(Long user_id, Long travel_id) throws EntityNotFoundException, EntityAlreadyExistException {

        Optional<UserModel> userDb=userRepository.findById(user_id);
        if(!userDb.isPresent()){
            throw new EntityNotFoundException("User not Found");
        }

        Optional<TravelPlan> travelDb=travelPlanRespository.findById(travel_id);
        if(!travelDb.isPresent()){
            throw new EntityNotFoundException(("Travel Plan does't exits"));
        }

        if(userDb.get().getTravelPlanList().contains(travelDb.get())){
            throw  new EntityAlreadyExistException("User Already taken the plan");
        }

        if(travelDb.get().getUserModelList().contains(userDb.get())){
            throw new EntityAlreadyExistException("User Already Exists");
        }

        userDb.get().getTravelPlanList().add(travelDb.get());
        travelDb.get().getUserModelList().add(userDb.get());

        userRepository.save(userDb.get());
        travelPlanRespository.save(travelDb.get());
        return new PurchaseModel(HttpStatus.FOUND,user_id,travel_id);


//
//        UserModel ne=userDb.get();
//        if(userDb.get().getTravelPlanList()==null){
//            userDb.get().setTravelPlanList(new ArrayList<TravelPlan>());
//
//        }
//        userDb.get().getTravelPlanList().add(travelDb.get());
//        if(travelDb.get().getUserModelList()==null){
//            travelDb.get().setUserModelList(new ArrayList<UserModel>());
//
//        }
//
//        List<UserModel> tmp=travelDb.get().getUserModelList();
//
//
////        travelDb.get().getUserModelList().add(userDb.get());
//        tmp.add(userDb.get());
//        travelDb.get().setUserModelList(tmp);
//        userDb.get().setTravelPlanList(userDb.get().getTravelPlanList());
//        userRepository.saveAndFlush(userDb.get());






//        ne.getTravelPlanList().add(travelDb.get());
//
//        userRepository.save(ne);
//
//        return true;





//        PurchaseModel purchaseModelNew=new PurchaseModel();
//        PurchaseModel purchaseModel=new PurchaseModel();
//        purchaseModel.setUserModel(userDb.get());
//
//        purchaseModel.setTravelPlan(travelDb.get());
//        purchaseModelRepository.save(purchaseModel);
//        return purchaseModel;







    }

    @Override
    public PurchaseModel exitFromPlan(Long user_id, Long travel_id) throws EntityNotFoundException {

        Optional<UserModel> userDb=userRepository.findById(user_id);
        if(!userDb.isPresent()){
            throw new EntityNotFoundException("User not found");
        }


        Optional<TravelPlan> travelDb=travelPlanRespository.findById(travel_id);
        if(!travelDb.isPresent()){
            throw new EntityNotFoundException("Travel plan not Found");
        }

        if(userDb.get().getTravelPlanList().indexOf(travelDb.get())==-1){
            throw new EntityNotFoundException("User have't taken the plan");
        }


        userDb.get().getTravelPlanList().remove(userDb.get().getTravelPlanList().indexOf(travelDb.get()));



        userRepository.save(userDb.get());

        return new PurchaseModel(HttpStatus.FOUND,user_id,travel_id);



    }

//    @Override
//    public PurchaseModel exitFromPlan(Long user_id,Long travel_id) throws UserNotFoundException {
//
//        Optional<UserModel> userDb=userRepository.findById(user_id);
//        if(!userDb.isPresent()){
//            throw new UserNotFoundException("User not Found");
//        }
//
//        Optional<TravelPlan> travelDb=travelPlanRespository.findById(travel_id);
//        if(!travelDb.isPresent()){
//            throw new UserNotFoundException(("Travel Plan does't exits"));
//        }
//
//
//        Optional<PurchaseModel> purchaseModelDb=purchaseModelRepository.findByUserIdAndPlanId(user_id,travel_id);
//
//        if(!purchaseModelDb.isPresent()){
//            throw new UserNotFoundException("Purchase not Found");
//        }
//
//        purchaseModelRepository.delete(purchaseModelDb.get());
//
//        return purchaseModelDb.get();
//
//
//
//
//    }

    @Override
    public List<TravelPlan> getAllPlanByUserId(Long id) throws EntityNotFoundException {

        Optional<UserModel> userDb=userRepository.findById(id);
        if(!userDb.isPresent()){
            throw new EntityNotFoundException("User not exits");
        }

        return userRepository.findPlansByUserId(id);
//        List<TravelPlan> listOfPlans=new ArrayList<>();
//        Optional<UserModel> userDb=userRepository.findById(id);
//        if(!userDb.isPresent()){
//            throw new UserNotFoundException("User Not Exist");
//        }

//        return userDb.get().;


//        for(TravelPlan purchaseModel:userDb.get().getTravelPlanList()){
//            listOfPlans.add(purchaseModel.getUserModelList());
//        }


//        return userDb.get().getTravelPlanList();




    }

    @Override
    public List<UserModel> getAllUsersofTravelPlan(Long id) throws EntityNotFoundException {
        Optional<TravelPlan> travelPlan=travelPlanRespository.findById(id);
        if(!travelPlan.isPresent()){
            throw new EntityNotFoundException("Travel Plan not Found");
        }
        return travelPlan.get().getUserModelList();

    }

    @Override
    public TravelPlan deleteTravelPlanById(Long id) throws EntityNotFoundException {

        Optional<TravelPlan> travelPlanDb=travelPlanRespository.findById(id);
        if(!travelPlanDb.isPresent()){
            throw new EntityNotFoundException("Travel plan does't exist");

        }

        travelPlanRespository.deleteById(id);
        return travelPlanDb.get();


    }



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}
