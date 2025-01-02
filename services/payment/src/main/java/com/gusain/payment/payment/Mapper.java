package com.gusain.payment.payment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Payment toPayment(@Valid PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
