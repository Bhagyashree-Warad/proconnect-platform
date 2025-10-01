package com.proconnect.repository;

import com.proconnect.entity.Job;
import com.proconnect.enums.JobStatus;
import com.proconnect.enums.JobType;
import com.proconnect.enums.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
    List<Job> findByType(JobType type);
    List<Job> findByCategory(JobCategory category);

    @Query("SELECT j FROM Job j WHERE j.title LIKE %:keyword% OR j.description LIKE %:keyword%")
    List<Job> searchByTitleOrDescription(String keyword);
}
