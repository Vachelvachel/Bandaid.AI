package com.example.API.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MedicalAdviceRequestDTO(
        @NotBlank(message = "User ID is required")
        String userId,
        
        @NotBlank(message = "Question is required")
        String question,
        
        String symptoms,
        
        String medicalHistory,
        
        List<String> currentMedications,
        
        List<String> allergies,
        
        String age,
        
        String gender,
        
        String urgencyLevel // LOW, MEDIUM, HIGH
        
) {}
