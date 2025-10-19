package com.example.medical_advice_backend.service;
import com.example.medical_advice_backend.model.User;
import com.example.medical_advice_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        if(userRepository.findByUserName(user.getUserName())!=null){
            throw new RuntimeException("The username already exists!");
        }
        if(user.getPassword().length()<6){
            throw new RuntimeException("The password is too short");
        }
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if(user ==null){
            throw new RuntimeException("The user not exists");
        }
        return user;
    }
}
