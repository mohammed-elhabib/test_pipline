package com.example.delivery_microservice.service;

import com.example.delivery_microservice.entity.CommuneEntity;
import com.example.delivery_microservice.entity.DeliveryPointLineEntity;
import com.example.delivery_microservice.repository.DeliveryPointLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPointLineService {
    private DeliveryPointLineRepository deliveryPointLineRepository;
    @Autowired
    public DeliveryPointLineService(DeliveryPointLineRepository deliveryPointLineRepository) {
        this.deliveryPointLineRepository = deliveryPointLineRepository;
    }
    public List<DeliveryPointLineEntity> findByIdEnterprise(int idEnterprise) {
        return deliveryPointLineRepository.findByIdEnterprise(idEnterprise);
    }



}
