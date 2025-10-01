package com.proconnect.dto.recruiter;

import com.proconnect.enums.SubscriptionPlan;
import com.proconnect.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterRequestDTO {

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String companyWebsite;

    private String profileImageUrl;

    private SubscriptionPlan subscriptionPlan;
    private SubscriptionStatus subscriptionStatus;
}
