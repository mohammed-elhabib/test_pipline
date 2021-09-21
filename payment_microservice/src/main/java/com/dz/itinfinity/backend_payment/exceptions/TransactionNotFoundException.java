package com.dz.itinfinity.backend_payment.exceptions;

import java.util.function.Supplier;

public class TransactionNotFoundException extends RuntimeException implements Supplier<TransactionNotFoundException> {
    public TransactionNotFoundException(String message){
        super(message);
    }

    @Override
    public TransactionNotFoundException get() {
        return  this;
    }
}
