package com.example.travelplan.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private Long user_id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    @OneToMany(mappedBy = "userModel")
    private List<PurchaseModel> purchaseModelList;





}
