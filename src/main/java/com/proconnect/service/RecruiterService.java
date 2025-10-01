package com.proconnect.service;

import com.proconnect.dto.recruiter.RecruiterRequestDTO;
import com.proconnect.dto.recruiter.RecruiterResponseDTO;
import com.proconnect.entity.Recruiter;
import com.proconnect.entity.User;
import com.proconnect.repository.RecruiterRepository;
import com.proconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final UserRepository userRepository;

    // CREATE RECRUITER
    public RecruiterResponseDTO createRecruiter(Long userId, RecruiterRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Recruiter recruiter = Recruiter.builder()
                .user(user)
                .companyName(request.getCompanyName())
                .companyWebsite(request.getCompanyWebsite())
                .profileImageUrl(request.getProfileImageUrl())
                .subscriptionPlan(request.getSubscriptionPlan())
                .subscriptionStatus(request.getSubscriptionStatus())
                .build();

        Recruiter saved = recruiterRepository.save(recruiter);
        return mapToDTO(saved);
    }

    // GET RECRUITER BY ID
    public RecruiterResponseDTO getRecruiterById(Long id) {
        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recruiter not found"));
        return mapToDTO(recruiter);
    }

    // GET ALL RECRUITERS
    public List<RecruiterResponseDTO> getAllRecruiters() {
        return recruiterRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE RECRUITER
    @Transactional
    public RecruiterResponseDTO updateRecruiter(Long id, RecruiterRequestDTO request) {
        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recruiter not found"));

        if (request.getCompanyName() != null) recruiter.setCompanyName(request.getCompanyName());
        if (request.getCompanyWebsite() != null) recruiter.setCompanyWebsite(request.getCompanyWebsite());
        if (request.getProfileImageUrl() != null) recruiter.setProfileImageUrl(request.getProfileImageUrl());
        if (request.getSubscriptionPlan() != null) recruiter.setSubscriptionPlan(request.getSubscriptionPlan());
        if (request.getSubscriptionStatus() != null) recruiter.setSubscriptionStatus(request.getSubscriptionStatus());

        return mapToDTO(recruiter);
    }

    // DELETE RECRUITER
    public void deleteRecruiter(Long id) {
        if (!recruiterRepository.existsById(id)) {
            throw new IllegalArgumentException("Recruiter not found");
        }
        recruiterRepository.deleteById(id);
    }

    // HELPER: Map entity to DTO
    private RecruiterResponseDTO mapToDTO(Recruiter recruiter) {
        User user = recruiter.getUser();
        List<Long> jobIds = recruiter.getJobsPosted() != null
                ? recruiter.getJobsPosted().stream().map(j -> j.getId()).toList()
                : List.of();

        return RecruiterResponseDTO.builder()
                .id(recruiter.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .companyName(recruiter.getCompanyName())
                .companyWebsite(recruiter.getCompanyWebsite())
                .profileImageUrl(recruiter.getProfileImageUrl())
                .subscriptionPlan(recruiter.getSubscriptionPlan())
                .subscriptionStatus(recruiter.getSubscriptionStatus())
                .jobsPostedIds(jobIds)
                .build();
    }
}
