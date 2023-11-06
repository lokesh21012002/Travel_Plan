package com.example.travelplan.models;


import com.example.travelplan.services.UserService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="travel_plan")

public class TravelPlan {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long plan_id;
    private String name;
    private String description;
    private String destination;
    private Date date=new Date();

//    @OneToMany(mappedBy = "travelPlan",fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "travelPlanList",fetch = FetchType.LAZY)

    private List<UserModel> userModelList=new ArrayList<UserModel>();

//    @OneToMany(mappedBy = "travel_plan",fetch = FetchType.EAGER)
//
////    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserModel userModel;


//    private List<PurchaseModel> purchaseModelList;





}
