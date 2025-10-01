package com.proconnect.service;

import com.proconnect.dto.jobseeker.JobSeekerRequestDTO;
import com.proconnect.dto.jobseeker.JobSeekerResponseDTO;
import com.proconnect.entity.Jobseeker;
import com.proconnect.entity.User;
import com.proconnect.repository.JobseekerRepository;
import com.proconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSeekerService {

    private final JobseekerRepository jobseekerRepository;
    private final UserRepository userRepository;

    // CREATE JOB SEEKER
    public JobSeekerResponseDTO createJobSeeker(Long userId, JobSeekerRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Jobseeker jobSeeker = Jobseeker.builder()
                .user(user)
                .profileImageUrl(request.getProfileImageUrl())
                .subscriptionPlan(request.getSubscriptionPlan())
                .subscriptionStatus(request.getSubscriptionStatus())
                .skills(request.getSkills())
                .certifications(request.getCertifications())
                .experience(request.getExperience())
                .build();

        Jobseeker saved = jobseekerRepository.save(jobSeeker);
        return mapToDTO(saved);
    }

    // GET JOB SEEKER BY ID
    public JobSeekerResponseDTO getJobSeekerById(Long id) {
        Jobseeker jobSeeker = jobseekerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));
        return mapToDTO(jobSeeker);
    }

    // GET ALL JOB SEEKERS
    public List<JobSeekerResponseDTO> getAllJobSeekers() {
        return jobseekerRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE JOB SEEKER
    @Transactional
    public JobSeekerResponseDTO updateJobSeeker(Long id, JobSeekerRequestDTO request) {
        Jobseeker jobSeeker = jobseekerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));

        if (request.getProfileImageUrl() != null) jobSeeker.setProfileImageUrl(request.getProfileImageUrl());
        if (request.getSubscriptionPlan() != null) jobSeeker.setSubscriptionPlan(request.getSubscriptionPlan());
        if (request.getSubscriptionStatus() != null) jobSeeker.setSubscriptionStatus(request.getSubscriptionStatus());
        if (request.getSkills() != null) jobSeeker.setSkills(request.getSkills());
        if (request.getCertifications() != null) jobSeeker.setCertifications(request.getCertifications());
        jobSeeker.setExperience(request.getExperience());

        return mapToDTO(jobSeeker);
    }

    // DELETE JOB SEEKER
    public void deleteJobSeeker(Long id) {
        if (!jobseekerRepository.existsById(id)) {
            throw new IllegalArgumentException("JobSeeker not found");
        }
        jobseekerRepository.deleteById(id);
    }

    // HELPER: Map entity to DTO
    private JobSeekerResponseDTO mapToDTO(Jobseeker jobSeeker) {
        User user = jobSeeker.getUser();
        return JobSeekerResponseDTO.builder()
                .id(jobSeeker.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .profileImageUrl(jobSeeker.getProfileImageUrl())
                .subscriptionPlan(jobSeeker.getSubscriptionPlan())
                .subscriptionStatus(jobSeeker.getSubscriptionStatus())
                .skills(jobSeeker.getSkills())
                .certifications(jobSeeker.getCertifications())
                .experience(jobSeeker.getExperience())
                .build();
    }
}
