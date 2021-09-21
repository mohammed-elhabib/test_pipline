package com.example.delivery_microservice.service;

import com.example.delivery_microservice.entity.CommuneEntity;
import com.example.delivery_microservice.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommuneService {
    private CommuneRepository communeRepository;
    @Autowired
    public CommuneService(CommuneRepository communeRepository) {

        this.communeRepository = communeRepository;
    }
    public CommuneEntity findByIdCommune(int idCommune) {

        return communeRepository.findByIdCommune(idCommune);
    }
    public Iterable<CommuneEntity> findAll() {

        return communeRepository.findAll();
    }

    public Iterable<CommuneEntity> findByIdWilaya(int idWilaya) {

        return communeRepository.findByIdWilaya(idWilaya);
    }
    public CommuneEntity findByNameCommune(String name) {

        return communeRepository.findByCommuneName(name);
    }

}
