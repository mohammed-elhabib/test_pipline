package com.example.delivery_microservice.service;

import com.example.delivery_microservice.entity.BuyerEntity;
import com.example.delivery_microservice.repository.AddressRepository;
import com.example.delivery_microservice.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {
    private BuyerRepository buyerRepository;
    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {

        this.buyerRepository= buyerRepository;
    }

    public void updateBuyer(int idAddress,int idBuyer)
    {
     buyerRepository.setBuyerIdAddress(idAddress,idBuyer);


    }

}
