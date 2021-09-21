package com.dz.itinfinity.backend_payment.exceptions;

public class TransactionNumberNullException extends RuntimeException{
    public TransactionNumberNullException(String message){
        super(message);
    }
}
