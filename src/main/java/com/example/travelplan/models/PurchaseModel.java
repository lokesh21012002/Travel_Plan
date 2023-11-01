package com.example.travelplan.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="purchase")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PurchaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private TravelPlan travelPlan;





    }
