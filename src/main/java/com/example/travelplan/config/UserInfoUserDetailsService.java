package com.example.travelplan.config;

import com.example.travelplan.exceptions.UserNotFoundException;
import com.example.travelplan.models.UserModel;
import com.example.travelplan.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserModel> user = userRepository.findByUsername(username);

        try {
            return user.map(UserInfoUserDetail::new)
                    .orElseThrow(()->new UserNotFoundException("user not found" + username));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
