package com.gusain.ecommerce.payment;

import com.gusain.ecommerce.customer.CustomerResponse;
import com.gusain.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
