package com.example.delivery_microservice.service;

import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.entity.DeliveryEntity;
import com.example.delivery_microservice.repository.AddressRepository;
import com.example.delivery_microservice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    private DeliveryRepository deliveryRepository;
    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {

        this.deliveryRepository= deliveryRepository;
    }
    public boolean saveDelivery(DeliveryEntity delivery) throws DataAccessException {
        try {
            deliveryRepository.save(delivery);

            return true;
        }catch (DataAccessException exp){

            return  false;
        }

    }
}
