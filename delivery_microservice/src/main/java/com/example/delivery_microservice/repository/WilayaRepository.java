package com.example.delivery_microservice.repository;

import com.example.delivery_microservice.entity.WilayaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WilayaRepository extends CrudRepository<WilayaEntity,Integer> {
    WilayaEntity findByIdWilaya(int idWilaya);
    WilayaEntity findByWilayaName(String wilayaName);
}
