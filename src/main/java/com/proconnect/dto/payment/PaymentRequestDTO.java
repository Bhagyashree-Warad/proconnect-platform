package com.proconnect.dto.payment;

import com.proconnect.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    @NotNull(message = "Amount is required")
    private Double amount;

    private String currency;

    @NotNull(message = "Payment status is required")
    private PaymentStatus status;

    private String transactionId;
    private String paymentMethod;

    private Long userId;
    private Long subscriptionId;
}
