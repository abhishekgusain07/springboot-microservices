package com.gusain.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class CustomerNotFoundException extends  RuntimeException{
    private final String msg;
    public CustomerNotFoundException(String msg) {
        super(msg); // Pass the message to the superclass constructor
        this.msg = msg; // Initialize the final variable
    }
}
