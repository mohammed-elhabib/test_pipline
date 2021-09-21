package dz.itinfinity.orderMS.Services.EntityServices;

import dz.itinfinity.orderMS.Entities.TablesEnt.OrderLine;
import dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions.OrderLineNotFoundException;
import dz.itinfinity.orderMS.IDComposites.OrderLineID;
import dz.itinfinity.orderMS.Repositories.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;

//////////////// CRUD ////////////////////////////////////////////////////////////////////////////////////////////////
@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private  Example<OrderLine> exampleOrderLine;

    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    /// ---------- Read --------------

    public List<OrderLine> findAllOrderLines(){
        return orderLineRepository.findAll();
    }

    public List<OrderLine> findAllByIdOrderLine(int idOrder){
        return orderLineRepository.findAllByIdOrder(idOrder);
    }

    public OrderLine findOrderLineByIDComposite(OrderLineID id){
        return orderLineRepository.findById(id).
                orElse(null);
           //     orElseThrow(() -> new OrderLineNotFoundException("OrderLine by idOrder: "+id.getIdOrder()+" and idProduct: "+id.getIdProduct()+" was not found"));
    }

    public OrderLine findByIdOrderLine(int id){
        return orderLineRepository.findByIdOrderLine(id);
    }

    /// ---------- Add --------------

    public OrderLine addOrderLine(OrderLine orderLine){
        orderLine = orderLineRepository.saveAndFlush(orderLine);
        return findOrderLineByIDComposite(new OrderLineID(orderLine.getIdOrder(),orderLine.getIdProduct()));
    }

    /// ---------- Delete --------------

    public void deleteOrderLine(OrderLine orderLine){
        orderLineRepository.delete(orderLine);
    }

    public void deleteOrderLineByIdOrderLine(int idOrderLine){
        orderLineRepository.deleteByIdOrderLine(idOrderLine);
    }

    public void deleteOrderLineByIDComposite(OrderLineID id){
        orderLineRepository.deleteById(id);
    }

    /// ---------- Update --------------

    public OrderLine updateOrderLine(OrderLine orderLine){
        OrderLine ol =findOrderLineByIDComposite(new OrderLineID(orderLine.getIdOrder(),orderLine.getIdProduct()));
        if(ol==null)
            orderLineRepository.save(orderLine);

        System.out.println("Quantity in service:  "+ orderLine.getQuantity());

        //  orderLineRepository.deleteById(new OrderLineID(orderLine.getIdOrder(),orderLine.getIdProduct()));
        return orderLineRepository.save(orderLine);
    }

    ////////////////////////////////// Others Services ///////////////////////////////////////

    /// ----------- find one orderLine by information --------------------------

    public OrderLine findOneOrderLineByInformation(OrderLine orderLine) {

        exampleOrderLine = Example.of(orderLine, ExampleMatcher.matchingAny());
        return orderLineRepository.findOne(exampleOrderLine).orElseThrow(() -> new OrderLineNotFoundException("This orderLine for the order num: '" + orderLine.getIdOrderLine()+"' & the product '"+orderLine.getIdProduct() + "' is not found in the system"));
    }

    /// ----------- test existing orderLine by information --------------------------

    public Boolean existOrderLineByInformation(OrderLine orderLine) {

        exampleOrderLine = Example.of(orderLine, ExampleMatcher.matchingAny());
        return orderLineRepository.exists(exampleOrderLine);
    }

}
