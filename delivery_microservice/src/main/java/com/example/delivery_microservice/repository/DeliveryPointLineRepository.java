package com.example.delivery_microservice.repository;



import com.example.delivery_microservice.entity.DeliveryPointLineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryPointLineRepository extends CrudRepository<DeliveryPointLineEntity,Long>
{
List<DeliveryPointLineEntity> findByIdEnterprise(int idEnterprise);

    List<DeliveryPointLineEntity> findAll();
}
