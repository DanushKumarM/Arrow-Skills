package com.as.payment_service.model;

import com.as.payment_service.enums.PaymentStatus;
import com.as.payment_service.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long courseId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentMethod;

    private String transactionId;

    private LocalDateTime paymentDate;

    @PrePersist
    public void paymentOn() {
        this.paymentDate = LocalDateTime.now();
    }
}
