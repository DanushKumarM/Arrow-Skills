package com.as.payment_service.dto;

import com.as.payment_service.enums.PaymentStatus;
import com.as.payment_service.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private Long userId;
    private Long courseId;
    private PaymentType paymentType;
}
