package com.example.delivery_microservice.repository;

import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.entity.CommuneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommuneRepository extends CrudRepository<CommuneEntity,Integer> {
  CommuneEntity findByIdCommune(int idCommune);
  List<CommuneEntity> findByIdWilaya(int idWilaya);
  CommuneEntity findByCommuneName(String communeName);


}
