package com.proconnect.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeResponseDTO {

    private Long id;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private boolean isActive;
    private LocalDateTime uploadedAt;

    private Long jobSeekerId;
    private String jobSeekerName;
}
