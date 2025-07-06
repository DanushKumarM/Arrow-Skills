package com.as.payment_service.controller;

import com.as.payment_service.dto.PaymentConfirmationRequest;
import com.as.payment_service.dto.PaymentRequest;
import com.as.payment_service.dto.PaymentResponse;
import com.as.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody PaymentRequest request,
                                                       @RequestHeader("Authorization") String token) {
        PaymentResponse response = paymentService.createPayment(request, token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPayment(@RequestBody PaymentConfirmationRequest confirmationRequest) {
        boolean confirmed = paymentService.confirmPayment(confirmationRequest);
        return confirmed ?  ResponseEntity.ok("Payment successful")
                : ResponseEntity.badRequest().body("Payment confirmation failed.");
    }
}
