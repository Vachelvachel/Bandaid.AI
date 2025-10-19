package com.example.API.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record MedicalAdviceResponseDTO(
        String adviceId,
        String userId,
        String question,
        String aiResponse,
        String confidence,
        List<String> recommendations,
        List<String> warnings,
        String disclaimer,
        LocalDateTime timestamp,
        String modelVersion
) {}
