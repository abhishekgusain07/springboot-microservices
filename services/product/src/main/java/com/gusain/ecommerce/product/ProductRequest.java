package com.gusain.ecommerce.product;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message="Product name is required")
        String name,

        @NotNull(message = "Product description is required")
        String description,

        @Positive(message="Available quantity should be positive")
        Double available_quantity,

        @Positive(message="Price should be positive")
        BigDecimal price,

        // todo: categoryId to category_id
        @NotNull(message="Category Id is required")
        Integer categoryId
) {
}
