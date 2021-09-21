package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ContactDto {


    private int idContact;


    private String firstNameS;


    private String lastNameS ;


    private Date birthDayS ;


    private String emailSeller;
    private String phoneSeller;


    private int idAddress;

}



