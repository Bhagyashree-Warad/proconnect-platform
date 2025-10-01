package com.proconnect.entity;

import com.proconnect.enums.SubscriptionPlan;
import com.proconnect.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "recruiters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String companyName;

    private String companyWebsite;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlan subscriptionPlan;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<Job> jobsPosted;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;
}
