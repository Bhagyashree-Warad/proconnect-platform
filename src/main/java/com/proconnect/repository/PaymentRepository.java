package com.proconnect.repository;

import com.proconnect.entity.Payment;
import com.proconnect.entity.Subscription;
import com.proconnect.entity.User;
import com.proconnect.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser(User user);
    List<Payment> findBySubscription(Subscription subscription);
    List<Payment> findByStatus(PaymentStatus status);
}
