package com.proconnect.repository;

import com.proconnect.entity.Subscription;
import com.proconnect.entity.Jobseeker;
import com.proconnect.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByJobSeeker(Jobseeker jobSeeker);
    List<Subscription> findByRecruiter(Recruiter recruiter);
}
