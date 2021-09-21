package com.dz.itinfinity.backend_payment.domain.DTOs;


import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SellerDto {

    int idEnterprise;
    String accountNumber;
    String enterpriseName;
    int idContact;
    int  idAddress;
    String webSite;
    String  taxIdentificationNumberS;
    String  registerCommercialNumberS;
    String accountType;
    String publicPrivate;
    String logoImage;
    float transportCosts;
    int deliveryTime;
    int reservationTime;
    private List<PaymentMethod> paymentMethods;




















}
