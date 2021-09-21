package com.example.delivery_microservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deliverypointline")
public class DeliveryPointLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDeliveryPointLine;
    @Column(name = "Address_idAddress")
    private int idAddress;
    @Column(name = "Enterprise_idEnterprise")
    private int idEnterprise;
    @Column(name = "deliveryPointNumber")
    private  int deliveryPointNumber;

}
