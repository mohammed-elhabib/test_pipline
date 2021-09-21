package dz.itinfinity.orderMS.Services.EntityServices;


import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
import dz.itinfinity.orderMS.Entities.TablesEnt.OrderPay;
import dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions.OrderNotFoundException;
import dz.itinfinity.orderMS.IDComposites.OrderID;
import dz.itinfinity.orderMS.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

//////////////// CRUD ////////////////////////////////////////////////////////////////////////////////////////////////
@Service
public class OrderService {

    @Autowired
    private OrderLineService orderLineService;
    private final OrderRepository orderRepository;
    private Example<OrderPay> exampleOrder;

    @Autowired
    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    /// ---------- Read --------------

    public List<OrderPay> findAllOrders(){

        return orderRepository.findAll();
    }

    public List<OrderPay> findOrderPayByIdBuyer(int idBuyer){

        return orderRepository.findAllByIdBuyer(idBuyer);
    }

    public List<OrderPay> findOrderPayByIdEnterprise(int idEnterprise){

        return orderRepository.findAllByIdEnterprise(idEnterprise);
    }

    public OrderPay findOrderByIDComposite(OrderID id){
        return orderRepository.findById(id).
                orElse(null);
           //     orElseThrow(() -> new OrderNotFoundException("OrderPay by idOrder: "+id.getIdOrder()+" and idEnterprise: "+id.getIdEnterprise()+" was not found"));
    }

    public OrderPay findOrderByIdOrder(int id){
        return orderRepository.findByIdOrder(id);
    }

    /// ---------- Add --------------

    public OrderPay addOrder(OrderPay order){
        //   order.setOrderNumber(UUID.randomUUID().toString());
       // OrderPay ord = addOrder1(order);

//        orderRepository.flush();

        return orderRepository.saveAndFlush(order);
    }

    /// ---------- Delete --------------

    public boolean deleteOrder(OrderPay order){
        orderRepository.delete(order);
        return true;
    }

    public boolean deleteOrderByIdOrder(int idOrder){
        orderRepository.deleteOrderPayByIdOrder(idOrder);
        return true;
    }

    public boolean deleteOrderByCompositeID(OrderID id){
        try {
            findOrderByIDComposite(id);
            orderRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    /// ---------- Update --------------

    public boolean updateStatusOrder(int idOrder, OrderStatus status) {
        OrderPay order = findOrderByIdOrder(idOrder);
        //    deleteOrderByCompositeID(new OrderID(order.getIdOrder(), order.getIdEnterprise()));
        order.setOrderStatus(status);

        orderRepository.save(order);

        return true;
    }

    public boolean updateStatusOrderCancel(int idOrder, OrderStatus status, String cancellationReason) {
        OrderPay order = findOrderByIdOrder(idOrder);
        // deleteOrderByCompositeID(new OrderID(order.getIdOrder(), order.getIdEnterprise()));
        order.setOrderStatus(status);
        order.setCancellationReason(cancellationReason);

        orderRepository.save(order);

        return true;
    }

    public OrderPay updateOrder(OrderPay order){
      //  deleteOrderByCompositeID(new OrderID(order.getIdOrder(),order.getIdEnterprise()));
        System.out.println("ORDER TVA 3:  " +order.getTva());
        System.out.println("ORDER TOTAL 3:  " +order.getOrderTotal());
        System.out.println("ORDER TAX 3:  " +order.getTotalOutsideTax());

      order.setIdOrder(order.getIdOrder()-1);
      orderRepository.save(order);

      order.setOrderStatus(OrderStatus.notConfirm);
      order.setIdOrder(order.getIdOrder()+1);

      //  orderRepository.setOrderPayById(order.getOrderTotal(), order.getTva(), order.getIdOrder());

      return orderRepository.save(order);

    /*    try {
           OrderPay ord= findOrderByCompositeID(new OrderID(order.getIdOrder(),order.getIdEnterprise()));
           ord.setOrderTotal(545777);
         //   return orderRepository.save(ord);
        }catch (Exception ex){
            return null;

        }*/
    }


    /////////////////////////// Others Services ///////////////////////////////////////////////////////////
    
    /// ----------- find one order by information --------------------------

    public OrderPay findOneOrderByInformation(OrderPay order) {

        exampleOrder = Example.of(order, ExampleMatcher.matchingAny());
        return orderRepository.findOne(exampleOrder).orElseThrow(() -> new OrderNotFoundException("The order '" + order.getOrderNumber()+"' is not found in the system"));
    }

    /// ----------- test existing order by information --------------------------

    public Boolean existOrderByInformation(OrderPay order) {

        exampleOrder = Example.of(order, ExampleMatcher.matchingAny());
        return orderRepository.exists(exampleOrder);
    }
//
//    public OrderPay getOrderFromOrderRequest(OrderedRequest orderRequest){
//        OrderPay order = new OrderPay();
//
//        order.setIdOrder(orderRequest.getIdOrder());
//        order.setOrderNumber(orderRequest.getOrderNumber());
//        order.setOrderTotal(orderRequest.getOrderTotal());
//        order.setDateOrder(orderRequest.getDateOrder());
//        order.setOrderStatus((OrderStatus) HelperService.getEnumItemChoice(OrderStatus.class, orderRequest.getOrderStatus()));
//        order.setTotalOutsideTax(orderRequest.getTotalOutsideTax());
//        order.setTva(orderRequest.getTva());
//        order.setCancellationReason(orderRequest.getCancellationReason());
//
//        return order;
//    }

}
