package com.example.travelplan.repository;

import com.example.travelplan.models.TravelPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelPlanRespository extends JpaRepository<TravelPlan,Long> {

    Optional<TravelPlan> findTravelPlanByName(String name);






}
