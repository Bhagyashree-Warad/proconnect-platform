package com.proconnect.dto.job;

import com.proconnect.enums.JobStatus;
import com.proconnect.enums.JobType;
import com.proconnect.enums.JobCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String location;
    private Double salary;
    private JobStatus status;
    private JobType type;
    private JobCategory category;
    private LocalDateTime postedAt;
    private LocalDateTime updatedAt;

    private Long recruiterId;
    private String recruiterName;
}
