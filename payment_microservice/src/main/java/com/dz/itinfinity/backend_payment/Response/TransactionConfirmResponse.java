package com.dz.itinfinity.backend_payment.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionConfirmResponse {
    String TransactionNumber;
    String url;
    String status;
    String payer;

}
