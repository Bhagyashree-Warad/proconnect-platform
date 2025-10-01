package com.proconnect.service;

import com.proconnect.dto.user.UserRequestDTO;
import com.proconnect.dto.user.UserResponseDTO;
import com.proconnect.enums.UserRole;
import com.proconnect.entity.User;
import jakarta.transaction.Transactional;
import com.proconnect.repository.RefreshTokenRepository;
import com.proconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    // Get all users (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get user by id (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDTO(user);
    }

    // Update user (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return mapToDTO(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        // Step 1: Delete all refresh tokens of the user
        refreshTokenRepository.deleteByUserId(userId);

        // Step 2: Delete the user
        userRepository.deleteById(userId);
    }
    
    // Helper method to map User to UserResponseDTO
    private UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .isEnabled(user.isEnabled())
                .isLocked(user.isLocked())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
