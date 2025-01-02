package com.gusain.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message="Product is mandatory")
        Integer productId,

        @NotNull(message="Product is mandatory")
        Double quantity
) {
}
