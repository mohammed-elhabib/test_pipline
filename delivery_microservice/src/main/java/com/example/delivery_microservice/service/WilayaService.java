package com.example.delivery_microservice.service;

import com.example.delivery_microservice.entity.CommuneEntity;
import com.example.delivery_microservice.entity.WilayaEntity;
import com.example.delivery_microservice.repository.CommuneRepository;
import com.example.delivery_microservice.repository.WilayaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WilayaService {
    private WilayaRepository wilayaRepository;
    @Autowired
    public WilayaService(WilayaRepository wilayaRepository) {

        this.wilayaRepository= wilayaRepository;
    }
    public WilayaEntity findByIdWilaya(int idWilaya) {

        return wilayaRepository.findByIdWilaya(idWilaya);
    }
    public Iterable<WilayaEntity> findAll() {

        return wilayaRepository.findAll();
    }
    public WilayaEntity findByNameWilaya(String nameWilaya) {

        return wilayaRepository.findByWilayaName(nameWilaya);
    }

}
