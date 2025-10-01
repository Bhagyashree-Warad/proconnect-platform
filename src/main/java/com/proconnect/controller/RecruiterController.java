package com.proconnect.controller;

import com.proconnect.dto.recruiter.RecruiterRequestDTO;
import com.proconnect.dto.recruiter.RecruiterResponseDTO;
import com.proconnect.service.RecruiterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
@RequiredArgsConstructor
public class RecruiterController {

    private final RecruiterService recruiterService;

    @PostMapping("/{userId}")
    public ResponseEntity<RecruiterResponseDTO> createRecruiter(
            @PathVariable Long userId,
            @Valid @RequestBody RecruiterRequestDTO request) {
        return ResponseEntity.ok(recruiterService.createRecruiter(userId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterResponseDTO> getRecruiterById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecruiterResponseDTO>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruiterResponseDTO> updateRecruiter(
            @PathVariable Long id,
            @Valid @RequestBody RecruiterRequestDTO request) {
        return ResponseEntity.ok(recruiterService.updateRecruiter(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.ok("Recruiter deleted successfully");
    }
}
