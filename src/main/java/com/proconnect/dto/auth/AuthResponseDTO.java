package com.proconnect.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {

    private String accessToken;
    private String message;
    private String refreshToken;
    @Builder.Default
    private String tokenType = "Bearer";

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
