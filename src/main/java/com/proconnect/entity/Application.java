package com.proconnect.entity;

import com.proconnect.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private Jobseeker jobSeeker;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Builder.Default
    private LocalDateTime appliedAt = LocalDateTime.now();

    private String resumeUrl;
}
