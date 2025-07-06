package com.as.payment_service.repository;

import com.as.payment_service.enums.PaymentStatus;
import com.as.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTransactionId(String orderId);
    Optional<Payment> findByUserIdAndCourseIdAndStatus(Long userId, Long courseId, PaymentStatus status);

}
