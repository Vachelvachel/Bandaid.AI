// src/main/java/com/example/api/dto/CreateUserDto.java
package com.example.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record CreateUserDto(
        @NotBlank String userName,
        @NotBlank String password,
        @Pattern(regexp = "\\d{10,15}", message = "Phone must be digits")
        String phone,
        @Email String email,
        boolean gender,
        String address,
        LocalDate date,
        List<String> emergencyContact,
        List<String> allergy,
        List<String> vaccine,
        List<String> medication
) {}