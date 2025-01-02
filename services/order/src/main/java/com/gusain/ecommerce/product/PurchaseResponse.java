package com.gusain.ecommerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
    Integer productId,
    String name,
    String description,
    Double quantity,
    BigDecimal price
) {
}
