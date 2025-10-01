package com.proconnect.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    private boolean isEnabled;
    private boolean isLocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
