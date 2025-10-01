package com.proconnect.dto.recruiter;

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
public class RecruiterResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String companyName;
    private String companyWebsite;
    private String profileImageUrl;

    private SubscriptionPlan subscriptionPlan;
    private SubscriptionStatus subscriptionStatus;

    private List<Long> jobsPostedIds;
}
