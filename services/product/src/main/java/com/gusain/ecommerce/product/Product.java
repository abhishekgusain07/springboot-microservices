package com.gusain.ecommerce.product;

import com.gusain.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double available_quantity;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
