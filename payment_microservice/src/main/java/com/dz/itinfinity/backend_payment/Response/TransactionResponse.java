package com.dz.itinfinity.backend_payment.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionResponse {
     String TransactionNumber;
     String url;
     String status;



}
