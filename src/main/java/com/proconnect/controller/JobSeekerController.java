package com.proconnect.controller;

import com.proconnect.dto.jobseeker.JobSeekerRequestDTO;
import com.proconnect.dto.jobseeker.JobSeekerResponseDTO;
import com.proconnect.service.JobSeekerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseekers")
@RequiredArgsConstructor
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    @PostMapping("/{userId}")
    public ResponseEntity<JobSeekerResponseDTO> createJobSeeker(
            @PathVariable Long userId,
            @Valid @RequestBody JobSeekerRequestDTO request) {
        return ResponseEntity.ok(jobSeekerService.createJobSeeker(userId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerResponseDTO> getJobSeekerById(@PathVariable Long id) {
        return ResponseEntity.ok(jobSeekerService.getJobSeekerById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobSeekerResponseDTO>> getAllJobSeekers() {
        return ResponseEntity.ok(jobSeekerService.getAllJobSeekers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeekerResponseDTO> updateJobSeeker(
            @PathVariable Long id,
            @Valid @RequestBody JobSeekerRequestDTO request) {
        return ResponseEntity.ok(jobSeekerService.updateJobSeeker(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobSeeker(@PathVariable Long id) {
        jobSeekerService.deleteJobSeeker(id);
        return ResponseEntity.ok("JobSeeker deleted successfully");
    }
}
