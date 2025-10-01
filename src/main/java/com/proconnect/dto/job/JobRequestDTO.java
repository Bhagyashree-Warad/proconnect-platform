package com.proconnect.dto.job;

import com.proconnect.enums.JobStatus;
import com.proconnect.enums.JobType;
import com.proconnect.enums.JobCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequestDTO {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    private String description;

    private String location;

    private Double salary;

    @NotNull(message = "Job status is required")
    private JobStatus status;

    @NotNull(message = "Job type is required")
    private JobType type;

    @NotNull(message = "Job category is required")
    private JobCategory category;
}
