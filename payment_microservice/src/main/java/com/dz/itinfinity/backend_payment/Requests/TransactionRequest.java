package com.dz.itinfinity.backend_payment.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {

    Integer order_idOrder;
    double amountTransaction;
    String enterpriseAccountNumber;

}
