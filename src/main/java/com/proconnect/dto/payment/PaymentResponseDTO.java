package com.proconnect.dto.payment;

import com.proconnect.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long id;
    private Double amount;
    private String currency;
    private PaymentStatus status;
    private String transactionId;
    private String paymentMethod;
    private LocalDateTime paymentDate;

    private Long userId;
    private String userName;

    private Long subscriptionId;
}
