package com.dz.itinfinity.backend_payment.domain.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private int idAddress;
    private String city;
    private String codeZip;
    private int idCommune;
    private String floor;
    private String homeNumber;
    private String street;
 //   private int wilaya;
}
