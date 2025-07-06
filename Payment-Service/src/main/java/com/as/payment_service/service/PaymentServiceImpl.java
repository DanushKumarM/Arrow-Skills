package com.as.payment_service.service;

import com.as.payment_service.client.CourseClient;
import com.as.payment_service.client.UserClient;
import com.as.payment_service.dto.*;
import com.as.payment_service.enums.PaymentStatus;
import com.as.payment_service.exception.CourseNotFoundException;
import com.as.payment_service.exception.DuplicatePaymentException;
import com.as.payment_service.exception.PaymentNotFoundException;
import com.as.payment_service.exception.UnauthorizedException;
import com.as.payment_service.mapper.PaymentMapper;
import com.as.payment_service.model.Payment;
import com.as.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final UserClient userClient;
    private final CourseClient courseClient;


    @Override
    public PaymentResponse createPayment(PaymentRequest request, String token) {

       try{
            UserResponse user = userClient.getUserId(request.getUserId(),token);
        }catch (Exception e){
            throw new UnauthorizedException("Invalid or expired token.");
       }

        CourseResponse course;
        try {
             course = courseClient.getCourseById(request.getCourseId(), token);
        }catch (Exception e) {
            throw new CourseNotFoundException("Course not found: ID " + request.getCourseId());
        }


        String fakeOrderId = "order_" + UUID.randomUUID().toString().replace("-", "").substring(0, 14);


        Optional<Payment> existingPayment = paymentRepository.findByUserIdAndCourseIdAndStatus(
                request.getUserId(),
                request.getCourseId(),
                PaymentStatus.SUCCESS
        );

        if (existingPayment.isPresent()) {
            throw new DuplicatePaymentException("You already purchased this course.");
        }



        Payment payment = paymentMapper.toEntity(request);
        payment.setAmount(course.getPrice());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTransactionId(fakeOrderId);

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public Boolean confirmPayment(PaymentConfirmationRequest confirmationRequest) {
        Payment payment = paymentRepository.findByTransactionId(confirmationRequest.getOrderId()).orElseThrow(()->
                new PaymentNotFoundException("No payment found for order ID: " + confirmationRequest.getOrderId()));

        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);
        return true;
    }
}
