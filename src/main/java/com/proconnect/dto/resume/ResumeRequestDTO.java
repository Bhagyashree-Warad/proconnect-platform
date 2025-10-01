package com.proconnect.dto.resume;

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
public class ResumeRequestDTO {

    @NotBlank(message = "File name is required")
    private String fileName;

    @NotBlank(message = "File URL is required")
    private String fileUrl;

    @NotBlank(message = "File type is required")
    private String fileType;

    private boolean isActive;

    @NotNull(message = "JobSeeker ID is required")
    private Long jobSeekerId;
}
