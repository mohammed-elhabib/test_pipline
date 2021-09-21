package com.example.delivery_microservice.dto;

import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.entity.DeliveryPointLineEntity;
import lombok.*;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryInfoDTO {

    int delay;
    float transportcosts;
    AddressPointLineDTO  deliveryPoint_seller;
    int enterprise_id;
    int order_id;
    List<AddressPointLineDTO> addressList;
    int buyer_id;

}
