package com.proconnect.service;

import com.proconnect.dto.analytics.AnalyticsRequestDTO;
import com.proconnect.dto.analytics.AnalyticsResponseDTO;
import com.proconnect.entity.Analytics;
import com.proconnect.entity.Job;
import com.proconnect.entity.User;
import com.proconnect.enums.AnalyticsType;
import com.proconnect.repository.AnalyticsRepository;
import com.proconnect.repository.JobRepository;
import com.proconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    // Create analytics record
    public AnalyticsResponseDTO createAnalytics(AnalyticsRequestDTO request) {
        User user = null;
        Job job = null;

        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }
        if (request.getJobId() != null) {
            job = jobRepository.findById(request.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found"));
        }

        Analytics analytics = Analytics.builder()
                .type(request.getType())
                .user(user)
                .job(job)
                .count(1L)
                .createdAt(LocalDateTime.now())
                .build();

        Analytics saved = analyticsRepository.save(analytics);
        return mapToResponse(saved);
    }

    // Get analytics by job
    public List<AnalyticsResponseDTO> getAnalyticsByJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return analyticsRepository.findByJob(job)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get analytics by user
    public List<AnalyticsResponseDTO> getAnalyticsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return analyticsRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Mapping method
    private AnalyticsResponseDTO mapToResponse(Analytics analytics) {
        return AnalyticsResponseDTO.builder()
                .id(analytics.getId())
                .type(analytics.getType())
                .jobId(analytics.getJob() != null ? analytics.getJob().getId() : null)
                .userId(analytics.getUser() != null ? analytics.getUser().getId() : null)
                .count(analytics.getCount())
                .createdAt(analytics.getCreatedAt())
                .build();
    }
}
