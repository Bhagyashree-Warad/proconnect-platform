package com.proconnect.repository;

import com.proconnect.entity.RefreshToken;
import com.proconnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

	
    void deleteByUserId(Long userId);

	Optional<RefreshToken> findByUser(User user);

}

