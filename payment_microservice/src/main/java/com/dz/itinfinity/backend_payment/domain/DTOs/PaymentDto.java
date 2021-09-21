package com.dz.itinfinity.backend_payment.domain.DTOs;

import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

   private PaymentMethod paymentMethod;
   private Integer bayerID;
   private Integer  sellerID;
   private Integer orderID;
   private Integer deliveryID;

}
