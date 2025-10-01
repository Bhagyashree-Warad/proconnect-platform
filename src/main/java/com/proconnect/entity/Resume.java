package com.proconnect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileUrl;

    private String fileType;

    @Builder.Default
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @Builder.Default
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private Jobseeker jobSeeker;
}
