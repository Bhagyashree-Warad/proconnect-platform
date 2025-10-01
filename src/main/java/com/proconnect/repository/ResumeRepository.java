package com.proconnect.repository;

import com.proconnect.entity.Resume;
import com.proconnect.entity.Jobseeker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByJobSeeker(Jobseeker jobSeeker);
    List<Resume> findByIsActiveTrueAndJobSeeker(Jobseeker jobSeeker);
}
