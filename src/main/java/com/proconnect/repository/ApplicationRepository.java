package com.proconnect.repository;

import com.proconnect.entity.Application;
import com.proconnect.entity.Job;
import com.proconnect.entity.Jobseeker;
import com.proconnect.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJob(Job job);
    List<Application> findByJobSeeker(Jobseeker jobSeeker);
    List<Application> findByStatus(ApplicationStatus status);
}
