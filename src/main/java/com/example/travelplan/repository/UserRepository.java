package com.example.travelplan.repository;

import com.example.travelplan.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    public Optional<UserModel> findByUsername(String username);








}
