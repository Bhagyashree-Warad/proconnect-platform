package com.proconnect.service;

import com.proconnect.dto.resume.ResumeRequestDTO;
import com.proconnect.dto.resume.ResumeResponseDTO;
import com.proconnect.entity.Jobseeker;
import com.proconnect.entity.Resume;
import com.proconnect.exception.ResourceNotFoundException;
import com.proconnect.repository.JobseekerRepository;
import com.proconnect.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final JobseekerRepository jobseekerRepository;

    // Create or upload a resume
    public ResumeResponseDTO createResume(ResumeRequestDTO requestDTO) {
        Jobseeker jobSeeker = jobseekerRepository.findById(requestDTO.getJobSeekerId())
                .orElseThrow(() -> new ResourceNotFoundException("JobSeeker not found with id: " + requestDTO.getJobSeekerId()));

        Resume resume = Resume.builder()
                .fileName(requestDTO.getFileName())
                .fileUrl(requestDTO.getFileUrl())
                .fileType(requestDTO.getFileType())
                .isActive(requestDTO.isActive())
                .uploadedAt(LocalDateTime.now())
                .jobSeeker(jobSeeker)
                .build();

        Resume savedResume = resumeRepository.save(resume);
        return mapToResponse(savedResume);
    }

    // Get all resumes
    public List<ResumeResponseDTO> getAllResumes() {
        return resumeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get resumes by JobSeeker
    public List<ResumeResponseDTO> getResumesByJobSeeker(Long jobSeekerId) {
        Jobseeker jobSeeker = jobseekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFoundException("JobSeeker not found with id: " + jobSeekerId));

        return resumeRepository.findByJobSeeker(jobSeeker)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get active resumes by JobSeeker
    public List<ResumeResponseDTO> getActiveResumesByJobSeeker(Long jobSeekerId) {
        Jobseeker jobSeeker = jobseekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFoundException("JobSeeker not found with id: " + jobSeekerId));

        return resumeRepository.findByIsActiveTrueAndJobSeeker(jobSeeker)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Update resume
    public ResumeResponseDTO updateResume(Long resumeId, ResumeRequestDTO requestDTO) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found with id: " + resumeId));

        resume.setFileName(requestDTO.getFileName());
        resume.setFileUrl(requestDTO.getFileUrl());
        resume.setFileType(requestDTO.getFileType());
        resume.setActive(requestDTO.isActive());

        Resume updatedResume = resumeRepository.save(resume);
        return mapToResponse(updatedResume);
    }

    // Delete resume
    public void deleteResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resume not found with id: " + resumeId));
        resumeRepository.delete(resume);
    }

    // Mapping method from entity to DTO
    private ResumeResponseDTO mapToResponse(Resume resume) {
        return ResumeResponseDTO.builder()
                .id(resume.getId())
                .fileName(resume.getFileName())
                .fileUrl(resume.getFileUrl())
                .fileType(resume.getFileType())
                .isActive(resume.isActive())
                .uploadedAt(resume.getUploadedAt())
                .jobSeekerId(resume.getJobSeeker().getId())
                .jobSeekerName(resume.getJobSeeker().getUser().getFirstName() + " " +
                               resume.getJobSeeker().getUser().getLastName())
                .build();
    }
}
