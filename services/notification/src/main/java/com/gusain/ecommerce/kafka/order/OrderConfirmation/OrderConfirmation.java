package com.gusain.ecommerce.kafka.order.OrderConfirmation;

import com.gusain.ecommerce.kafka.order.Customer;
import com.gusain.ecommerce.kafka.order.Product;
import com.gusain.ecommerce.kafka.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
