package com.gusain.ecommerce.kafka;

import com.gusain.ecommerce.customer.CustomerResponse;
import com.gusain.ecommerce.order.PaymentMethod;
import com.gusain.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;

import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
