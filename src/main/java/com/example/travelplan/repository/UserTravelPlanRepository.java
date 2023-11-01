package com.example.travelplan.repository;

import com.example.travelplan.models.PurchaseModel;
import com.example.travelplan.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTravelPlanRepository extends JpaRepository<PurchaseModel,Long> {

    @Query("SELECT sp.userModel FROM PurchaseModel sp WHERE sp.travelPlan.plan_id = :planId")
    List<UserModel> findByPlanId(Long planId);
}
