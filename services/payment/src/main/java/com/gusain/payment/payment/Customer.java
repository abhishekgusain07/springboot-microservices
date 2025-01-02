package com.gusain.payment.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,

        @NotNull(message = "FirstName is required")
        String firstname,

        @NotNull(message = "LastName is required")
        String lastname,

        @NotNull(message = "Email is required")
        @Email(message = "Email is not valid")
        String email
) {
}
