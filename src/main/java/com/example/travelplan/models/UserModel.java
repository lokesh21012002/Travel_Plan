package com.example.travelplan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="user")
@Data
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Long user_id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
//    @OneToMany(mappedBy = "userModel")
   @ManyToMany(fetch=FetchType.LAZY)


   @JoinTable(name = "purchase",



           joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="plan_id"))

   @JsonIgnore

     private List<TravelPlan> travelPlanList=new ArrayList<TravelPlan>();






}
