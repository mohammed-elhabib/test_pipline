package com.example.delivery_microservice.controller;


import com.example.delivery_microservice.entity.CommuneEntity;
import com.example.delivery_microservice.entity.WilayaEntity;
import com.example.delivery_microservice.service.CommuneService;
import com.example.delivery_microservice.service.WilayaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/delivery")
public class CommuneController {

    private CommuneService communeService;
    private WilayaService wilayaService;
    @Autowired
    public  CommuneController(CommuneService communeService,WilayaService wilayaService){


        this.communeService=communeService;
        this.wilayaService=wilayaService;
    }
    @GetMapping("/commune")
    public Iterable<CommuneEntity> getAllCommune(){
        return communeService.findAll();
    }

    @GetMapping("/communeByIdWilaya/{idWilaya}")
    public Iterable<CommuneEntity> getAllCommuneByIdWilaya(@PathVariable("idWilaya") int idWilaya)
    {

        return communeService.findByIdWilaya(idWilaya);
    }


}
