package com.dz.itinfinity.backend_payment.domain.DTOs;

import com.dz.itinfinity.backend_payment.domain.enums.DeliveryStatus;
import com.dz.itinfinity.backend_payment.domain.enums.DeliveryType;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class DeliveryDto {
    private  int idDeliver;
    private  String deliveryNumber;
    private  DeliveryType deliveryType;
    private  String linkGPS;
    private  String phoneNumber;
    private  String email;
    private Date startDateDelivery;
    private  int deliveryDuration;
    private  int idAddress;
    private int idEnterprise;
    private int  idOrder;
    private Date  endDateDelivery;
    private DeliveryStatus deliveryStatus;

    private String phone;















}
