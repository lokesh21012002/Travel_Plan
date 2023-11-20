package com.example.travelplan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table (name="user")
@Data
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserModel implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    private Long user_id;

    @Column(unique = true)
    private String username;
    private String password;
    private Role  role;
//    @OneToMany(mappedBy = "userModel")
   @ManyToMany(fetch=FetchType.EAGER
//           ,cascade = CascadeType.REMOVE
           , cascade = CascadeType.DETACH

   )


   @JoinTable(name = "purchase",



           joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="plan_id"))

   @JsonIgnore

     private List<TravelPlan> travelPlanList=new ArrayList<TravelPlan>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
