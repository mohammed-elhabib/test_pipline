package dz.itinfinity.orderMS.Repositories;

import dz.itinfinity.orderMS.Entities.TablesEnt.Buyer;
import dz.itinfinity.orderMS.IDComposites.BuyerID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, BuyerID> {
     Buyer findByIdBuyer(int idBuyer);

     @Query(value="SELECT b.idBuyer FROM Buyer b where b.firstNameB = ?1 AND b.lastNameB = ?2 AND b.birthDayB =?3 ",nativeQuery=true)
     int findIdBuyer(String firstNameB, String lastNameB, Date birthDayB);

     void deleteByIdBuyer(int idBuyer);
}