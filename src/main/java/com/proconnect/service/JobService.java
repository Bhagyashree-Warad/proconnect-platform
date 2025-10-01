package com.proconnect.service;

import com.proconnect.dto.job.JobRequestDTO;
import com.proconnect.dto.job.JobResponseDTO;
import com.proconnect.entity.Job;
import com.proconnect.entity.Recruiter;
import com.proconnect.repository.JobRepository;
import com.proconnect.repository.RecruiterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final RecruiterRepository recruiterRepository;

    // CREATE JOB
    public JobResponseDTO createJob(Long recruiterId, JobRequestDTO request) {
        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new IllegalArgumentException("Recruiter not found"));

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .salary(request.getSalary())
                .status(request.getStatus())
                .type(request.getType())
                .category(request.getCategory())
                .recruiter(recruiter)
                .build();

        Job saved = jobRepository.save(job);
        return mapToDTO(saved);
    }

    // GET JOB BY ID
    public JobResponseDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        return mapToDTO(job);
    }

    // GET ALL JOBS
    public List<JobResponseDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE JOB
    @Transactional
    public JobResponseDTO updateJob(Long id, JobRequestDTO request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));

        if (request.getTitle() != null) job.setTitle(request.getTitle());
        if (request.getDescription() != null) job.setDescription(request.getDescription());
        if (request.getLocation() != null) job.setLocation(request.getLocation());
        if (request.getSalary() != null) job.setSalary(request.getSalary());
        if (request.getStatus() != null) job.setStatus(request.getStatus());
        if (request.getType() != null) job.setType(request.getType());
        if (request.getCategory() != null) job.setCategory(request.getCategory());

        return mapToDTO(job);
    }

    // DELETE JOB
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new IllegalArgumentException("Job not found");
        }
        jobRepository.deleteById(id);
    }

    // SEARCH JOBS BY KEYWORD
    public List<JobResponseDTO> searchJobs(String keyword) {
        return jobRepository.searchByTitleOrDescription(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // HELPER: Map entity → DTO
    private JobResponseDTO mapToDTO(Job job) {
        Recruiter recruiter = job.getRecruiter();
        return JobResponseDTO.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .salary(job.getSalary())
                .status(job.getStatus())
                .type(job.getType())
                .category(job.getCategory())
                .postedAt(job.getPostedAt())
                .updatedAt(job.getUpdatedAt())
                .recruiterId(recruiter != null ? recruiter.getId() : null)
                .recruiterName(recruiter != null ? recruiter.getUser().getFirstName() + " " + recruiter.getUser().getLastName() : null)
                .build();
    }
}
