package com.proconnect.repository;

import com.proconnect.entity.User;
import com.proconnect.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email (used for login/authentication)
    Optional<User> findByEmail(String email);

    // Check if a user exists by email (used for registration)
    boolean existsByEmail(String email);

    // Get all users by role
    List<User> findAllByRole(UserRole role);
}
