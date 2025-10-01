package com.proconnect.dto.jobseeker;

import com.proconnect.enums.SubscriptionPlan;
import com.proconnect.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSeekerRequestDTO {

    @NotBlank(message = "Profile image URL is required")
    private String profileImageUrl;

    private SubscriptionPlan subscriptionPlan;
    private SubscriptionStatus subscriptionStatus;

    private List<String> skills;
    private List<String> certifications;

    private int experience;
}
