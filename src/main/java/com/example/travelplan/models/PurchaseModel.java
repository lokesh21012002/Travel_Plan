package com.example.travelplan.models;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name="purchase")
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseModel {

    private HttpStatus status;
    private Long user_id;
    private Long plan_id;

}

//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private UserModel userModel;
//
//    @ManyToOne
//    @JoinColumn(name = "plan_id")
//    @JsonIgnore
//    private TravelPlan travelPlan;
//
//
//
//
//
//    }
