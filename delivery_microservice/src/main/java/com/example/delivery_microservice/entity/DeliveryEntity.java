package com.example.delivery_microservice.entity;

import com.example.delivery_microservice.composite_Id.DeliveryId;
import com.example.delivery_microservice.enums.DeliveryStatusEnum;
import com.example.delivery_microservice.enums.DeliveryTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.Size;


import java.sql.Date;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DeliveryId.class)
@Table(name="delivery")
public class DeliveryEntity {

    @Column(unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long  idDelivery;

    @Id
    @Column(name = "deliveryNumber")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Size(max = 45)

    private  String  deliveryNumber;
    @Id
    @Column(name = "Enterprise_idEnterprise")
    private  int idEnterprise;
    @Id
    @Column(name = "startDateDelivery")
    private Date startDateDelivery;


    @Column(name = "Order_idOrder")
    private int idOrder;

    @Column(name = "Address_idAddress")
    private int idAddress;

    @Column(name = "endDateDelivery")
    private Date endDateDelivery;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryStatus")
    private DeliveryStatusEnum deliveryStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliveryType")
    private DeliveryTypeEnum deliveryType;

    @Column(name = "deliveryDuration")
    private int deliveryDuration;

    @Column(name = "linkGPS")
    private String linkGPS;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "Address_idAddress", referencedColumnName = "idAddress",insertable = false, updatable = false)
    private AddressEntity address;









}
