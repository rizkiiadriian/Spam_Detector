package com.spamdetector.spamdetector.service;

import com.spamdetector.spamdetector.model.SpamRequest;
import com.spamdetector.spamdetector.model.SpamResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpamDetectionService {

    private static final String PYTHON_API_URL = "http://localhost:5000/predict";
    private final RestTemplate restTemplate;

    public SpamDetectionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SpamResponse detectSpam(SpamRequest spamRequest) {  // No @Override needed
        try {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            // Create HTTP request body
            HttpEntity<SpamRequest> requestEntity = new HttpEntity<>(spamRequest, headers);

            // Send POST request
            ResponseEntity<SpamResponse> responseEntity = restTemplate.exchange(
                    PYTHON_API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    SpamResponse.class
            );

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to Python API: " + e.getMessage());
        }
    }
}
