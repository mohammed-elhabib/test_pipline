package com.dz.itinfinity.backend_payment.apis;

import lombok.Data;
import org.springframework.stereotype.Service;




public interface PaymentApi {
   <I,O> O create(I input);
   <I,O> O confirmPayment(I input);
}
