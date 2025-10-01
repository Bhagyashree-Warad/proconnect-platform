package com.proconnect.service;

import com.proconnect.dto.application.ApplicationRequestDTO;
import com.proconnect.dto.application.ApplicationResponseDTO;
import com.proconnect.entity.Application;
import com.proconnect.entity.Job;
import com.proconnect.entity.Jobseeker;
import com.proconnect.repository.ApplicationRepository;
import com.proconnect.repository.JobRepository;
import com.proconnect.repository.JobseekerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final JobseekerRepository jobseekerRepository;

    // CREATE APPLICATION
    public ApplicationResponseDTO createApplication(ApplicationRequestDTO request) {
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        Jobseeker jobSeeker = jobseekerRepository.findById(request.getJobSeekerId())
                .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));

        Application application = Application.builder()
                .job(job)
                .jobSeeker(jobSeeker)
                .resumeUrl(request.getResumeUrl())
                .status(request.getStatus())
                .build();

        Application saved = applicationRepository.save(application);
        return mapToDTO(saved);
    }

    // GET APPLICATION BY ID
    public ApplicationResponseDTO getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        return mapToDTO(application);
    }

    // GET ALL APPLICATIONS
    public List<ApplicationResponseDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE APPLICATION
    @Transactional
    public ApplicationResponseDTO updateApplication(Long id, ApplicationRequestDTO request) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        if (request.getResumeUrl() != null) {
            application.setResumeUrl(request.getResumeUrl());
        }
        if (request.getStatus() != null) {
            application.setStatus(request.getStatus());
        }

        return mapToDTO(application);
    }

    // DELETE APPLICATION
    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new IllegalArgumentException("Application not found");
        }
        applicationRepository.deleteById(id);
    }

    // HELPER: Map entity to DTO
    private ApplicationResponseDTO mapToDTO(Application application) {
        return ApplicationResponseDTO.builder()
                .id(application.getId())
                .jobId(application.getJob().getId())
                .jobTitle(application.getJob().getTitle())
                .jobSeekerId(application.getJobSeeker().getId())
                .jobSeekerName(application.getJobSeeker().getUser().getFirstName() + " " +
                        application.getJobSeeker().getUser().getLastName())
                .resumeUrl(application.getResumeUrl())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }
}
