package com.example.delivery_microservice.composite_Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryId implements Serializable {



    private  String  deliveryNumber;

    private  int idEnterprise;

    private Date startDateDelivery;
}
