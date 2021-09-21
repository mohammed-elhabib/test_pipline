package com.example.delivery_microservice.composite_Id;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerId implements Serializable {


    private String firstNameB;


    private String  lastNameB;


    private Date birthDayB;
}
