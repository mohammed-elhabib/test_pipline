package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class OrderDto {
    private int idOrder;
    private float orderTotal;
    private float tva;
    private float  totalOutsideTax;
    private float orderNumber;
    Date dateOrder;
    int  idEnterprise;
    int idBuyer;
   String  orderStatus;














}
