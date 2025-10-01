package com.proconnect.dto.analytics;

import com.proconnect.enums.AnalyticsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsResponseDTO {

    private Long id;
    private AnalyticsType type;
    private Long jobId;
    private Long userId;
    private Long count;
    private LocalDateTime createdAt;
}
