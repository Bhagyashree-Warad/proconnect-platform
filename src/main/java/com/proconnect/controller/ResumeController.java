package com.proconnect.controller;

import com.proconnect.dto.resume.ResumeRequestDTO;
import com.proconnect.dto.resume.ResumeResponseDTO;
import com.proconnect.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    // Create/upload a resume
    @PostMapping
    public ResponseEntity<ResumeResponseDTO> createResume(@Valid @RequestBody ResumeRequestDTO requestDTO) {
        ResumeResponseDTO responseDTO = resumeService.createResume(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all resumes
    @GetMapping
    public ResponseEntity<List<ResumeResponseDTO>> getAllResumes() {
        List<ResumeResponseDTO> resumes = resumeService.getAllResumes();
        return ResponseEntity.ok(resumes);
    }

    // Get resumes by JobSeeker
    @GetMapping("/jobseeker/{jobSeekerId}")
    public ResponseEntity<List<ResumeResponseDTO>> getResumesByJobSeeker(@PathVariable Long jobSeekerId) {
        List<ResumeResponseDTO> resumes = resumeService.getResumesByJobSeeker(jobSeekerId);
        return ResponseEntity.ok(resumes);
    }

    // Get active resumes by JobSeeker
    @GetMapping("/jobseeker/{jobSeekerId}/active")
    public ResponseEntity<List<ResumeResponseDTO>> getActiveResumesByJobSeeker(@PathVariable Long jobSeekerId) {
        List<ResumeResponseDTO> resumes = resumeService.getActiveResumesByJobSeeker(jobSeekerId);
        return ResponseEntity.ok(resumes);
    }

    // Update resume
    @PutMapping("/{resumeId}")
    public ResponseEntity<ResumeResponseDTO> updateResume(
            @PathVariable Long resumeId,
            @Valid @RequestBody ResumeRequestDTO requestDTO) {
        ResumeResponseDTO updatedResume = resumeService.updateResume(resumeId, requestDTO);
        return ResponseEntity.ok(updatedResume);
    }

    // Delete resume
    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeId) {
        resumeService.deleteResume(resumeId);
        return ResponseEntity.noContent().build();
    }
}
