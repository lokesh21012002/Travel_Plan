package com.example.travelplan.repository;

import com.example.travelplan.models.TravelPlan;
import com.example.travelplan.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    public  UserModel findFirstByUsername(String username);
//    UserModel findFirstByUserName(String username);

    public Optional<UserModel> findByUsername(String username);


    @Query("select s.travelPlanList from UserModel s where s.user_id=:user_id")
    public List<TravelPlan> findPlansByUserId(Long user_id);








}
