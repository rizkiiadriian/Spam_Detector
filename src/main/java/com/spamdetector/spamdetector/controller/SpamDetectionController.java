package com.spamdetector.spamdetector.controller;
//nambahin getmapping dashboard


import com.spamdetector.spamdetector.model.SpamRequest;
import com.spamdetector.spamdetector.model.SpamResponse;
import com.spamdetector.spamdetector.service.SpamDetectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spam-detection")
public class SpamDetectionController {

    private final SpamDetectionService spamDetectionService;

    public SpamDetectionController(SpamDetectionService spamDetectionService) {
        this.spamDetectionService = spamDetectionService;
    }

    @PostMapping("/detect")
    public ResponseEntity<SpamResponse> detectSpam(@RequestBody SpamRequest spamRequest) {
        try {
            SpamResponse spamResponse = spamDetectionService.detectSpam(spamRequest);
            return new ResponseEntity<>(spamResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}