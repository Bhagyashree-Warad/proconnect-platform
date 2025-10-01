package com.proconnect.repository;

import com.proconnect.entity.Jobseeker;
import com.proconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Long> {
    Optional<Jobseeker> findByUser(User user);
}
