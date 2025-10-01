package com.proconnect.service;

import com.proconnect.dto.payment.PaymentRequestDTO;
import com.proconnect.dto.payment.PaymentResponseDTO;
import com.proconnect.entity.Payment;
import com.proconnect.entity.Subscription;
import com.proconnect.entity.User;
import com.proconnect.repository.PaymentRepository;
import com.proconnect.repository.SubscriptionRepository;
import com.proconnect.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    // CREATE PAYMENT
    public PaymentResponseDTO createPayment(PaymentRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Subscription subscription = null;
        if (request.getSubscriptionId() != null) {
            subscription = subscriptionRepository.findById(request.getSubscriptionId())
                    .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        }

        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency() != null ? request.getCurrency() : "INR")
                .status(request.getStatus())
                .transactionId(request.getTransactionId())
                .paymentMethod(request.getPaymentMethod())
                .user(user)
                .subscription(subscription)
                .build();

        Payment saved = paymentRepository.save(payment);
        return mapToDTO(saved);
    }

    // GET BY ID
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
        return mapToDTO(payment);
    }

    // GET ALL
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // UPDATE PAYMENT
    @Transactional
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        if (request.getAmount() != null) payment.setAmount(request.getAmount());
        if (request.getCurrency() != null) payment.setCurrency(request.getCurrency());
        if (request.getStatus() != null) payment.setStatus(request.getStatus());
        if (request.getTransactionId() != null) payment.setTransactionId(request.getTransactionId());
        if (request.getPaymentMethod() != null) payment.setPaymentMethod(request.getPaymentMethod());

        return mapToDTO(payment);
    }

    // DELETE
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new IllegalArgumentException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    // HELPER: map entity → DTO
    private PaymentResponseDTO mapToDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .paymentMethod(payment.getPaymentMethod())
                .paymentDate(payment.getPaymentDate())
                .userId(payment.getUser() != null ? payment.getUser().getId() : null)
                .userName(payment.getUser() != null ? payment.getUser().getFirstName() + " " + payment.getUser().getLastName() : null)
                .subscriptionId(payment.getSubscription() != null ? payment.getSubscription().getId() : null)
                .build();
    }
}
