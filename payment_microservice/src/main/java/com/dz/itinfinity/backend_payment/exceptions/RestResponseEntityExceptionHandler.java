package com.dz.itinfinity.backend_payment.exceptions;

import com.dz.itinfinity.backend_payment.domain.DTOs.MessageError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = {BuyerIdNullException.class})
    protected ResponseEntity<MessageError> handleBuyerIdNullException(RuntimeException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return ResponseEntity.ok(MessageError.builder().message(List.of(ex.getMessage())).status(HttpStatus.BAD_REQUEST).build());
    }

    @ExceptionHandler(value = {OrderIdNullException.class})
    protected ResponseEntity<MessageError> handleOrderIdNullException(RuntimeException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return ResponseEntity.ok(MessageError.builder().message(List.of(ex.getMessage())).status(HttpStatus.BAD_REQUEST).build());
    }

    @ExceptionHandler(value = {SellerIdNullException.class})
    protected ResponseEntity<MessageError> handleSellerIdNullException(RuntimeException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return ResponseEntity.ok(MessageError.builder().message(List.of(ex.getMessage())).status(HttpStatus.BAD_REQUEST).build());
    }

    @ExceptionHandler(value = {TransactionNumberNullException.class})
    protected ResponseEntity<MessageError> handleTransactionNumberNullException(RuntimeException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return ResponseEntity.ok(MessageError.builder().message(List.of(ex.getMessage())).status(HttpStatus.BAD_REQUEST).build());
    }

    @ExceptionHandler(value = {PaymentMethodNotSupportedException.class})
    protected ResponseEntity<MessageError> handlePaymentMethodNotSupportedException(RuntimeException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return ResponseEntity.ok(MessageError.builder().message(List.of(ex.getMessage())).status(HttpStatus.NOT_IMPLEMENTED).build());
    }

    @ExceptionHandler(value = {TransactionNotFoundException.class})
    protected ResponseEntity<MessageError> handleTransactionNotFoundException(RuntimeException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return ResponseEntity.ok(MessageError.builder().message(List.of(ex.getMessage())).status(HttpStatus.FAILED_DEPENDENCY).build());
    }
}