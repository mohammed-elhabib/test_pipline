package dz.itinfinity.orderMS.Repositories;

import dz.itinfinity.orderMS.Entities.TablesEnt.OrderPay;
import dz.itinfinity.orderMS.IDComposites.OrderID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderPay, OrderID> {

        public OrderPay findByIdOrder(int idOrder);
        public List<OrderPay> findAllByIdBuyer(int idBuyer);
        public List<OrderPay> findAllByIdEnterprise(int idEnterprise);
        public void deleteOrderPayByIdOrder(int idOrder);

//        @Modifying
//        @Query("update OrderPay o set o.orderTotal = ?1, o.tva = ?2 where o.idOrder = ?3")
//        public void setOrderPayById(float orderTotal, float tva, int idOrder);

}