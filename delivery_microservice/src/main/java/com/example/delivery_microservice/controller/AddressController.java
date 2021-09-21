package com.example.delivery_microservice.controller;


import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.entity.CommuneEntity;
import com.example.delivery_microservice.service.AddressService;
import com.example.delivery_microservice.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;
    @Autowired
    public  AddressController(AddressService addressService){

        this.addressService=addressService;
    }
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,

            produces = MediaType.APPLICATION_JSON_VALUE)
    public int addAddress( @RequestBody AddressEntity addressEntity){

        return addressService.saveAddress(addressEntity);


    }


}
