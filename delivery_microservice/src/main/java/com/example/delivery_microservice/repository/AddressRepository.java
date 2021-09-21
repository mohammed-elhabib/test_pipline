package com.example.delivery_microservice.repository;

import com.example.delivery_microservice.entity.AddressEntity;
import com.example.delivery_microservice.entity.DeliveryPointLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Integer> {
   public AddressEntity findByIdAddress(int idAddress);

   @Query(value="SELECT a.idAddress FROM address a where a.Commune_idCommune = ?1 AND a.city = ?2 AND a.floor =?3 AND a.street = ?4 AND a.homeNumber = ?5",nativeQuery=true)
   public int findIdAddress(int idcommune,String city,String floor,String street,String home);


}
