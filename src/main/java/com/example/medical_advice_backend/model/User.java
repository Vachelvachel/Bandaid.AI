package com.example.medical_advice_backend.model;

import java.io.*;
import java.time.LocalDate;

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String phone;
    private String email;

    private boolean gender;
    private String address;
    private LocalDate date;

    private List<String> emergincyContact;
    private List<String> allergy;
    private List<String> vaccine;
    private List<String> meditation;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getGender(){
        return gender;
    }

    public void setGender(String gender) throws IllegalArguementException{
        if (gender == null || gender.equals("")){
            throw new IllegalArguementException("Please enter your gender");
        }
        if (gender.toLowerCase() == "male") {
            this.gender = false;
        } else if (gender.toLowerCase() == "female"){
            this.gender = true;
        } else {
            throw new IllegalArguementException("Please enter your gender");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phoen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate() {
        this.date = LocalDate.now();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}