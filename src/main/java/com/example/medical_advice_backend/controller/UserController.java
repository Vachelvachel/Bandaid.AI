package com.example.medical_advice_backend.controller;

import com.example.medical_advice_backend.model.User;
import com.example.medical_advice_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try{
            User newUser = userService.register(user);
            return ResponseEntity.ok(newUser);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body){
        try{
            String userName = body.get("userName");
            String password = body.get("password");
            User newUser = userService.login(userName, password);
            return ResponseEntity.ok(newUser);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
