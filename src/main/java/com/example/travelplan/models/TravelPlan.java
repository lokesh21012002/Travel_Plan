package com.example.travelplan.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="travel_plan")

public class TravelPlan {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long plan_id;
    private String name;
    private String description;
    private String destination;
    private Date date=new Date();

    @OneToMany(mappedBy = "travelPlan",fetch = FetchType.EAGER)

    private List<PurchaseModel> purchaseModelList;




}
