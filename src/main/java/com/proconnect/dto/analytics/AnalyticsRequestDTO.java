package com.proconnect.dto.analytics;

import com.proconnect.enums.AnalyticsType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsRequestDTO {

    @NotNull(message = "Analytics type is required")
    private AnalyticsType type;

    private Long jobId;
    private Long userId;
}
