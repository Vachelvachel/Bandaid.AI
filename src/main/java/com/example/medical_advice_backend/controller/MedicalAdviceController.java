package com.example.medical_advice_backend.controller;

import com.example.API.DTO.MedicalAdviceRequestDTO;
import com.example.API.DTO.MedicalAdviceResponseDTO;
import com.example.medical_advice_backend.service.SageMakerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/medical-advice")
@CrossOrigin(origins = "*")
public class MedicalAdviceController {

    @Autowired
    private SageMakerService sageMakerService;

    @PostMapping("/ask")
    public ResponseEntity<?> getMedicalAdvice(@Valid @RequestBody MedicalAdviceRequestDTO request) {
        try {
            MedicalAdviceResponseDTO response = sageMakerService.getMedicalAdvice(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Failed to get medical advice",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "healthy",
                "service", "Medical Advice API",
                "timestamp", java.time.LocalDateTime.now()
        ));
    }
}
