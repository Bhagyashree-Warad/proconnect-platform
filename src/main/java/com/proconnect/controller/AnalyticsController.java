package com.proconnect.controller;

import com.proconnect.dto.analytics.AnalyticsRequestDTO;
import com.proconnect.dto.analytics.AnalyticsResponseDTO;
import com.proconnect.service.AnalyticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    // Create analytics record
    @PostMapping
    public ResponseEntity<AnalyticsResponseDTO> createAnalytics(
            @Valid @RequestBody AnalyticsRequestDTO request) {
        AnalyticsResponseDTO response = analyticsService.createAnalytics(request);
        return ResponseEntity.ok(response);
    }

    // Get analytics by job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<AnalyticsResponseDTO>> getAnalyticsByJob(@PathVariable Long jobId) {
        List<AnalyticsResponseDTO> analytics = analyticsService.getAnalyticsByJob(jobId);
        return ResponseEntity.ok(analytics);
    }

    // Get analytics by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnalyticsResponseDTO>> getAnalyticsByUser(@PathVariable Long userId) {
        List<AnalyticsResponseDTO> analytics = analyticsService.getAnalyticsByUser(userId);
        return ResponseEntity.ok(analytics);
    }
}
