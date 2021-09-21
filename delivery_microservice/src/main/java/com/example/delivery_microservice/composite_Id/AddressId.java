package com.example.delivery_microservice.composite_Id;

import com.example.delivery_microservice.entity.CommuneEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressId implements Serializable {
    private  String  homeNumber;
    private String floor;
    private String street;
    private String city;
    private int idCommune;

}
