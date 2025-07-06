package com.as.payment_service.service;


import com.as.payment_service.dto.PaymentConfirmationRequest;
import com.as.payment_service.dto.PaymentRequest;
import com.as.payment_service.dto.PaymentResponse;
import com.as.payment_service.model.Payment;

public interface PaymentService {

     PaymentResponse createPayment(PaymentRequest request,String token);

    Boolean confirmPayment(PaymentConfirmationRequest confirmationRequest);

}
