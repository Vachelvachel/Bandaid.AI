package com.example.medical_advice_backend.service;

import com.example.API.DTO.MedicalAdviceRequestDTO;
import com.example.API.DTO.MedicalAdviceResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sagemakerruntime.SageMakerRuntimeClient;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointRequest;
import software.amazon.awssdk.services.sagemakerruntime.model.InvokeEndpointResponse;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SageMakerService {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.sagemaker.endpoint-name}")
    private String endpointName;

    @Value("${aws.access-key-id}")
    private String accessKeyId;

    @Value("${aws.secret-access-key}")
    private String secretAccessKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MedicalAdviceResponseDTO getMedicalAdvice(MedicalAdviceRequestDTO request) {
        try {
            // Create SageMaker client
            SageMakerRuntimeClient client = createSageMakerClient();

            // Prepare input data for the model
            Map<String, Object> inputData = prepareInputData(request);
            String jsonInput = objectMapper.writeValueAsString(inputData);

            // Create invoke request
            InvokeEndpointRequest invokeRequest = InvokeEndpointRequest.builder()
                    .endpointName(endpointName)
                    .contentType("application/json")
                    .body(SdkBytes.fromUtf8String(jsonInput))
                    .build();

            // Invoke the endpoint
            InvokeEndpointResponse response = client.invokeEndpoint(invokeRequest);
            String responseBody = response.body().asUtf8String();

            // Parse the response
            return parseSageMakerResponse(request, responseBody);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get medical advice from SageMaker: " + e.getMessage(), e);
        }
    }

    private SageMakerRuntimeClient createSageMakerClient() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        
        return SageMakerRuntimeClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }

    private Map<String, Object> prepareInputData(MedicalAdviceRequestDTO request) {
        Map<String, Object> inputData = new HashMap<>();
        
        // Prepare the input in the format expected by your SageMaker model
        inputData.put("question", request.question());
        inputData.put("symptoms", request.symptoms());
        inputData.put("medical_history", request.medicalHistory());
        inputData.put("current_medications", request.currentMedications());
        inputData.put("allergies", request.allergies());
        inputData.put("age", request.age());
        inputData.put("gender", request.gender());
        inputData.put("urgency_level", request.urgencyLevel());
        
        return inputData;
    }

    private MedicalAdviceResponseDTO parseSageMakerResponse(MedicalAdviceRequestDTO request, String responseBody) {
        try {
            // Parse the JSON response from SageMaker
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
            
            String aiResponse = (String) responseMap.getOrDefault("response", "No response available");
            String confidence = String.valueOf(responseMap.getOrDefault("confidence", "0.0"));
            
            // Extract recommendations and warnings if available
            List<String> recommendations = extractListFromResponse(responseMap, "recommendations");
            List<String> warnings = extractListFromResponse(responseMap, "warnings");
            
            return new MedicalAdviceResponseDTO(
                    UUID.randomUUID().toString(),
                    request.userId(),
                    request.question(),
                    aiResponse,
                    confidence,
                    recommendations,
                    warnings,
                    "This advice is for informational purposes only and should not replace professional medical consultation.",
                    LocalDateTime.now(),
                    "1.0"
            );
            
        } catch (Exception e) {
            // Fallback response if parsing fails
            return new MedicalAdviceResponseDTO(
                    UUID.randomUUID().toString(),
                    request.userId(),
                    request.question(),
                    "I apologize, but I'm having trouble processing your request. Please try again or consult with a healthcare professional.",
                    "0.0",
                    Arrays.asList("Consult with a healthcare professional"),
                    Arrays.asList("Unable to process request"),
                    "This advice is for informational purposes only and should not replace professional medical consultation.",
                    LocalDateTime.now(),
                    "1.0"
            );
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> extractListFromResponse(Map<String, Object> responseMap, String key) {
        Object value = responseMap.get(key);
        if (value instanceof List) {
            return (List<String>) value;
        }
        return new ArrayList<>();
    }
}
