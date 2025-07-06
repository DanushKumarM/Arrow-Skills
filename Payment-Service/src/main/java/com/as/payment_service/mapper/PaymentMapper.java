package com.as.payment_service.mapper;

import com.as.payment_service.dto.PaymentRequest;
import com.as.payment_service.dto.PaymentResponse;
import com.as.payment_service.enums.PaymentStatus;
import com.as.payment_service.enums.PaymentType;
import com.as.payment_service.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequest request){
        return Payment.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .paymentMethod(request.getPaymentType())
                .build();
    }

   public PaymentResponse toResponse(Payment payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .userId(payment.getUserId())
                .courseId(payment.getCourseId())
                .amount(payment.getAmount())
                .status(payment.getStatus().name())
                .paymentMethod(payment.getPaymentMethod().name())
                .transactionId(payment.getTransactionId())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
