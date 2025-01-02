package com.gusain.ecommerce.product;

import java.math.BigDecimal;

public record  ProductResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Double available_quantity,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {

}
