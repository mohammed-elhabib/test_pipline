package com.example.delivery_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressPointLineDTO {
    private int idAddress;
    private String wilaya;
    private String commune;
    private String city;
    private String street;
    private  String homeNumber;
    private String floor;


}
