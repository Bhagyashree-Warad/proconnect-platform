package com.proconnect.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogoutRequestDTO {

    @NotBlank(message = "Refresh token is required")
    private String refreshToken;
}
