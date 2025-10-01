package com.proconnect.dto.application;

import com.proconnect.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationRequestDTO {

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "JobSeeker ID is required")
    private Long jobSeekerId;

    private String resumeUrl;

    private ApplicationStatus status;
}
