package com.example.delivery_microservice.controller;


import com.example.delivery_microservice.entity.WilayaEntity;
import com.example.delivery_microservice.service.WilayaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/delivery")
public class WilayaController {
    private WilayaService wilayaService;
    @Autowired
    public  WilayaController(WilayaService wilayaService){
        this.wilayaService=wilayaService;
    }
    @GetMapping("/wilaya")
    public Iterable<WilayaEntity> getAllWilaya(){

        return wilayaService.findAll();
    }
    @GetMapping (value = "/wilayaId/{name}")
    public int getWilayaId( @PathVariable("name") String name){
        System.out.println(name);
        if(wilayaService.findByNameWilaya(name)!=null)
        return wilayaService.findByNameWilaya(name).getIdWilaya();
        return 0;

    }

    @GetMapping("/wilayaById/{idWilaya}")
    public String getNameWilayaByIdWilaya(@PathVariable("idWilaya") int idWilaya)
    {

        if(wilayaService.findByIdWilaya(idWilaya)!=null){

            return wilayaService.findByIdWilaya(idWilaya).getWilayaName();
        }
        return "";
    }

}
