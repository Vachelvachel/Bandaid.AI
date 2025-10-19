package com.example.medical_advice_backend.repository;

import com.example.medical_advice_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
//    public Optional<User> findByEmail(String email);
}

