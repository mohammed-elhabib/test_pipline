package com.example.delivery_microservice.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    int delay;
    float transportcosts;
    int  idAddress_deliveryPoint;
    int enterprise_id;
     int buyer_id;//


}
