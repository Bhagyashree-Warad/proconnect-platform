package com.proconnect.repository;

import com.proconnect.entity.Analytics;
import com.proconnect.entity.Job;
import com.proconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {
    List<Analytics> findByJob(Job job);
    List<Analytics> findByUser(User user);
}
