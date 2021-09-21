package com.example.delivery_microservice.entity;

import com.example.delivery_microservice.composite_Id.BuyerId;
import com.example.delivery_microservice.enums.BuyerStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(BuyerId.class)
@Table(name = "buyer")
@DynamicUpdate
public class BuyerEntity {

    @Column(name = "idBuyer",unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  idBuyer ;

    @Id
    @Column(name = "firstNameB")
    private String firstNameB;

    @Id
    @Column(name = "lastNameB")
    private String  lastNameB;

    @Id
    @Column(name = "birthDayB")
    private Date birthDayB;

    @Column(name = "Address_idAddress")
    private int  idAddress;

    @Column(name = "emailBuyer")
    private String  emailBuyer;

    @Column(name = "phoneBuyer")
    private String   phoneBuyer;

    @Enumerated(EnumType.STRING)
    @Column(name = "buyerStatus")
    private BuyerStatusEnum buyerStatus;

    @Column(name = "registerCommercialNumberB")
    private String    registerCommercialNumberB;

    @Column(name = "taxIdentificationNumberB")
    private String taxIdentificationNumberB;

}
