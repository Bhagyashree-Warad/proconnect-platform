package com.proconnect.dto.file;

import com.proconnect.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDTO {

    private Long id;
    private FileType fileType;
    private String url;
    private Long uploadedById;
    private String uploadedByName;
    private LocalDateTime uploadedAt;
}
