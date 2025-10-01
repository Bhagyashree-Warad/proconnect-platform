package com.proconnect.entity;

import com.proconnect.enums.SubscriptionPlan;
import com.proconnect.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "job_seekers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jobseeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private SubscriptionPlan subscriptionPlan;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    @ElementCollection
    private List<String> certifications;

    @ElementCollection
    private List<String> skills;

    @Builder.Default
    private int experience = 0;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Application> applications;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Resume> resumes;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;
}
