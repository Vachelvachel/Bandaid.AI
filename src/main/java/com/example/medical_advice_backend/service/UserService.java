package com.example.medical_advice_backend.service;

import com.example.medical_advice_backend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User register(User user);
    public User login(String userName, String password);
}
