package dz.itinfinity.orderMS.Repositories;

import dz.itinfinity.orderMS.Entities.TablesEnt.OrderLine;
import dz.itinfinity.orderMS.IDComposites.OrderLineID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLineID> {
    public OrderLine findByIdOrderLine(int idOrderLine);
    public List<OrderLine> findAllByIdOrder(int idOrder);
    public void deleteByIdOrderLine(int idOrderLine);
}