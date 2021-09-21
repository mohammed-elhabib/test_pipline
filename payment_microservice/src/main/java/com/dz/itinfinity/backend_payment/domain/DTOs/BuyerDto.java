package com.dz.itinfinity.backend_payment.domain.DTOs;

import com.dz.itinfinity.backend_payment.domain.enums.BuyerStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class BuyerDto {

    private int idBuyer;
    private String firstNameB;
    private String lastNameB;
    private String phoneBuyer;
    private BuyerStatus buyerStatus;
    private String taxIdentificationNumber;
    private String emailBuyer;
    private String registerCommercialNumberB;
    private String companyName;
    private int idAddress;
    private Date birthDayB;















}
