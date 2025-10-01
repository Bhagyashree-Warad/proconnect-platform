package com.proconnect.dto.jobseeker;

import com.proconnect.enums.SubscriptionPlan;
import com.proconnect.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSeekerResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String profileImageUrl;
    private SubscriptionPlan subscriptionPlan;
    private SubscriptionStatus subscriptionStatus;
    private List<String> skills;
    private List<String> certifications;
    private int experience;
}
