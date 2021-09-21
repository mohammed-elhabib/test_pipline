package com.dz.itinfinity.backend_payment.exceptions;

public class PaymentMethodNotSupportedException extends RuntimeException {
    public PaymentMethodNotSupportedException(String message) {
        super(message);
    }
}
