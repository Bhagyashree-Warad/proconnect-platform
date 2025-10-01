package com.proconnect.dto.file;

import com.proconnect.enums.FileType;
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
public class FileRequestDTO {

    @NotNull(message = "File type is required")
    private FileType fileType;

    @NotBlank(message = "File URL is required")
    private String url;

    private Long uploadedById;
}
