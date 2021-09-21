package com.example.delivery_microservice.controller;


import com.example.delivery_microservice.entity.BuyerEntity;
import com.example.delivery_microservice.entity.DeliveryEntity;
import com.example.delivery_microservice.service.AddressService;
import com.example.delivery_microservice.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/buyer")
public class BuyerController {
    private BuyerService buyerService;
    @Autowired
    public  BuyerController(BuyerService buyerService){

        this.buyerService=buyerService;
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,

            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void updateBuyer(@RequestBody BuyerEntity buyer ){
        

       buyerService.updateBuyer(buyer.getIdAddress(),buyer.getIdBuyer());


    }



}
