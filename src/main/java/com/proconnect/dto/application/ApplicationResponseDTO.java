package com.proconnect.dto.application;

import com.proconnect.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponseDTO {

    private Long id;

    private Long jobId;
    private String jobTitle;

    private Long jobSeekerId;
    private String jobSeekerName;

    private String resumeUrl;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}
