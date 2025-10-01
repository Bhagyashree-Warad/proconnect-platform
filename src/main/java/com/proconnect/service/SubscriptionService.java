package com.proconnect.service;

import com.proconnect.dto.subscription.SubscriptionRequestDTO;
import com.proconnect.dto.subscription.SubscriptionResponseDTO;
import com.proconnect.entity.Jobseeker;
import com.proconnect.entity.Recruiter;
import com.proconnect.entity.Subscription;
import com.proconnect.repository.JobseekerRepository;
import com.proconnect.repository.RecruiterRepository;
import com.proconnect.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final JobseekerRepository jobseekerRepository;
    private final RecruiterRepository recruiterRepository;

    // CREATE SUBSCRIPTION
    public SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO request) {

        Jobseeker jobSeeker = null;
        Recruiter recruiter = null;

        if (request.getJobSeekerId() != null) {
            jobSeeker = jobseekerRepository.findById(request.getJobSeekerId())
                    .orElseThrow(() -> new IllegalArgumentException("JobSeeker not found"));
        }

        if (request.getRecruiterId() != null) {
            recruiter = recruiterRepository.findById(request.getRecruiterId())
                    .orElseThrow(() -> new IllegalArgumentException("Recruiter not found"));
        }

        Subscription subscription = Subscription.builder()
                .plan(request.getPlan())
                .status(request.getStatus())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .amountPaid(request.getAmountPaid())
                .jobSeeker(jobSeeker)
                .recruiter(recruiter)
                .build();

        Subscription saved = subscriptionRepository.save(subscription);
        return mapToDTO(saved);
    }

    // GET BY ID
    public SubscriptionResponseDTO getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        return mapToDTO(subscription);
    }

    // GET ALL
    public List<SubscriptionResponseDTO> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE SUBSCRIPTION
    @Transactional
    public SubscriptionResponseDTO updateSubscription(Long id, SubscriptionRequestDTO request) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        if (request.getPlan() != null) subscription.setPlan(request.getPlan());
        if (request.getStatus() != null) subscription.setStatus(request.getStatus());
        if (request.getStartDate() != null) subscription.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) subscription.setEndDate(request.getEndDate());
        if (request.getAmountPaid() != null) subscription.setAmountPaid(request.getAmountPaid());

        return mapToDTO(subscription);
    }

    // DELETE
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new IllegalArgumentException("Subscription not found");
        }
        subscriptionRepository.deleteById(id);
    }

    // HELPER: map entity → DTO
    private SubscriptionResponseDTO mapToDTO(Subscription subscription) {
        return SubscriptionResponseDTO.builder()
                .id(subscription.getId())
                .plan(subscription.getPlan())
                .status(subscription.getStatus())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .amountPaid(subscription.getAmountPaid())
                .jobSeekerId(subscription.getJobSeeker() != null ? subscription.getJobSeeker().getId() : null)
                .jobSeekerName(subscription.getJobSeeker() != null ? subscription.getJobSeeker().getUser().getFirstName() + " " + subscription.getJobSeeker().getUser().getLastName() : null)
                .recruiterId(subscription.getRecruiter() != null ? subscription.getRecruiter().getId() : null)
                .recruiterName(subscription.getRecruiter() != null ? subscription.getRecruiter().getUser().getFirstName() + " " + subscription.getRecruiter().getUser().getLastName() : null)
                .build();
    }
}
