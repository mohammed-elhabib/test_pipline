package com.example.delivery_microservice.repository;

import com.example.delivery_microservice.entity.BuyerEntity;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends CrudRepository<BuyerEntity,Integer>
{

   public  BuyerEntity findByIdBuyer(int idBuyer);
   @Modifying
   @Query(value="update buyer b set b.Address_idAddress = ?1 where b.idBuyer = ?2",nativeQuery=true)
   void setBuyerIdAddress(int idAddress,int idBuyer);
}
