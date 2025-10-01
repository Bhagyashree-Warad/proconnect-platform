
package com.proconnect.dto.subscription;

import com.proconnect.enums.SubscriptionPlan;
import com.proconnect.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionRequestDTO {

    @NotNull(message = "Subscription plan is required")
    private SubscriptionPlan plan;

    @NotNull(message = "Subscription status is required")
    private SubscriptionStatus status;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Double amountPaid;

    private Long jobSeekerId;
    private Long recruiterId;
}

