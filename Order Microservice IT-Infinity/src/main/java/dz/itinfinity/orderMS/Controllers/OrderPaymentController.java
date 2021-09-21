package dz.itinfinity.orderMS.Controllers;

import dz.itinfinity.orderMS.Entities.Enums.EnterpriseStatus;
import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
import dz.itinfinity.orderMS.Entities.TablesEnt.Buyer;
import dz.itinfinity.orderMS.Entities.TablesEnt.Enterprise;
import dz.itinfinity.orderMS.Entities.TablesEnt.OrderLine;
import dz.itinfinity.orderMS.Entities.TablesEnt.OrderPay;
import dz.itinfinity.orderMS.IDComposites.OrderLineID;
import dz.itinfinity.orderMS.Requests.Order2Request;
import dz.itinfinity.orderMS.Requests.OrderRequest;
import dz.itinfinity.orderMS.Requests.ProductRequest;
import dz.itinfinity.orderMS.Services.EntityServices.*;
import dz.itinfinity.orderMS.Services.OthersServices.ValidationSyntaxRequest;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("")

public class OrderPaymentController {
    @Autowired
    VerificationDataRequest verificationDataRequest;

    @Autowired
    ValidationSyntaxRequest validationSyntaxRequest;

    private ProductService productService;
    private EnterpriseService enterpriseService;
    private BuyerService buyerService;
    private OrderService orderService;
    private OrderLineService orderLineService;

    public OrderPaymentController( OrderLineService orderLineService,
                                   EnterpriseService enterpriseService,
                                   OrderService orderService,
                                   BuyerService buyerService,
                                   ProductService productService ){

        this.orderLineService = orderLineService;
        this.orderService = orderService;
        this.buyerService = buyerService;
        this.enterpriseService = enterpriseService;
        this.productService = productService;
    }

///////////////////////////////////////////////////////////// Controllers ////////////////////////////////////////////////////////////////////////
    @GetMapping(path ="/test/{idOrder}")
    public List<OrderLine> getAllOrder(@PathVariable int idOrder){
        return orderLineService.findAllByIdOrderLine(idOrder);
    }

    ///////// ----------------------- Get Order ------------------------/////////////////////////////
    @GetMapping(path ="/getAllOrders")
    public List<OrderPay> getAllOrder(){
        return orderService.findAllOrders();
    }

    @GetMapping(path ="/getAllOrdersForEnterprise/{idEnterprise}")
    public List<OrderPay> getAllOrderForEnterprise(@PathVariable int idEnterprise){
          return orderService.findOrderPayByIdEnterprise(idEnterprise);
    }

    @GetMapping(path ="/getAllOrdersForBuyer/{idBuyer}")
    public List<OrderPay> getAllOrderForBuyer(@PathVariable int idBuyer){
        return orderService.findOrderPayByIdBuyer(idBuyer);
    }

    @GetMapping(path ="/getOrderId/{idOrder}")
    public OrderPay getOrderByIdOrder(@PathVariable int idOrder){
        return orderService.findOrderByIdOrder(idOrder);
    }

    @GetMapping(path ="/getOrder/{idOrder}")
    public Order2Request getOrderByID(@PathVariable int idOrder){

        OrderRequest orderRequest = verificationDataRequest.fromOrderToOrderRequest(orderService.findOrderByIdOrder(idOrder));



        Order2Request order2Request = verificationDataRequest.fromOrderToOrder2Request(orderService.findOrderByIdOrder(idOrder));
        System.out.println("req 2 "+ order2Request.getDateOrder().toString());

        order2Request.setIdOrder(idOrder);

        order2Request.setEnterprise(verificationDataRequest.fromEnterpriseToEnterpriseRequest(enterpriseService.findEnterpriseByID(orderRequest.getIdEnterprise())));
        order2Request.setBuyer(verificationDataRequest.fromBuyerToBuyerRequest(buyerService.findBuyerByID(orderRequest.getIdBuyer())));
        order2Request.getBuyer().setIdBuyer(orderRequest.getIdBuyer());

        List<ProductRequest> productRequests = verificationDataRequest.orderLineProductsFromOrderLine(idOrder);
        ProductRequest[] productRequest = new ProductRequest[productRequests.size()];

        for(int i=0; i< productRequests.size();i++){
            productRequest[i] = productRequests.get(i);
        }
        order2Request.setOrderLineProducts(productRequest);

        return order2Request;

    }

    ///////// ----------------------- Add Order ------------------------ /////////////////////////////

    @PostMapping(path ="/addOrder")
    public int addNewOrder(@RequestBody OrderRequest orderRequest){
        int idOrder=82, idBuyer=0, idEnterprise=0;

        OrderPay order = null;
        OrderLine orderLine = null;
        Buyer buyer = verificationDataRequest.getExistBuyer(orderRequest.getBuyer());
        Enterprise enterprise = verificationDataRequest.getExistEnterprise(orderRequest.getEnterprise()) ;

        if (enterprise != null)
        System.out.println("order date "+orderRequest.getDateOrder());

        if (enterprise != null)
            if (enterprise.getEnterpriseStatus() == EnterpriseStatus.active)
                if (validationSyntaxRequest.validationInputsOrder(orderRequest))
                    if(verificationDataRequest.isOrderLineProductsExist(orderRequest.getOrderLineProducts(), orderRequest.getEnterprise().getIdEnterprise()))
                        if(verificationDataRequest.isAvailableQuantity(orderRequest.getOrderLineProducts(), orderRequest.getEnterprise().getIdEnterprise()))
                            if(verificationDataRequest.isCorrectTotalPrice(orderRequest, orderRequest.getEnterprise().getIdEnterprise())){

                                if( buyer == null){
                                    buyer = verificationDataRequest.fromBuyerRequestToBuyer(orderRequest.getBuyer());
                                    buyer = buyerService.addBuyer(buyer);
                                }

                                idBuyer = buyerService.findBuyerByIdGetId(buyer);
                                System.out.println("idBuyer  "+idBuyer);

                                idEnterprise = enterprise.getIdEnterprise();
                                System.out.println("idEnterprise  "+idEnterprise);

                                order = verificationDataRequest.fromOrderRequestToOrder(orderRequest);
                                System.out.println("orderStatus for request  "+order.getOrderStatus());

                                order.setIdBuyer(idBuyer);
                                order.setIdEnterprise(idEnterprise);
                                System.out.println("orderDate for request "+order.getDateOrder());

                                idOrder = orderService.addOrder(order).getIdOrder();
                                System.out.println("idOrder after 1st save "+idOrder);

                                order.setOrderStatus(OrderStatus.notConfirm);

                                idOrder =orderService.addOrder(order).getIdOrder();
                                System.out.println("idOrder after 2nd save  "+ idOrder);

                                order.setOrderStatus(OrderStatus.notConfirm);

                                System.out.println("idOrder  "+idOrder+ " product : " +orderRequest.getOrderLineProducts().size());

                                boolean orderLineAdded = verificationDataRequest.isAddListOfOrderLine(orderRequest.getOrderLineProducts(), idEnterprise,idOrder);
                                if(!orderLineAdded) return 0;
                            }

        return idOrder;
    }

    ///////// ----------------------- Update Order ------------------------/////////////////////////////

    @PutMapping(path ="/updateOrder")
    public boolean updateOrder(@RequestBody OrderRequest orderRequest){

        System.out.println("idOrder:  "+orderRequest.getIdOrder());
        System.out.println("idBuyer:  "+orderRequest.getBuyer().getBirthDayB());

        orderRequest.setIdBuyer(orderRequest.getBuyer().getIdBuyer());
        List<ProductRequest> orderLineList = verificationDataRequest.orderLineProductsFromOrderLine(orderRequest.getIdOrder());
        int size = orderRequest.getOrderLineProducts().size();
        int sizeDB = orderLineList.size();

        boolean isExist=false;

        if(size != sizeDB){
            for (int j = 0; j < sizeDB; j++) {
                for (int i = 0; i < size; i++) {
                    if (orderRequest.getOrderLineProducts().get(i).getProductName() == orderLineList.get(j).getProductName()) {
                        isExist = true;
                    }else{
                        isExist = false;
                    }
                }

                if (!isExist){
                    orderLineService.deleteOrderLine(verificationDataRequest.fromOrderLineRequestToOrderLine(orderLineList.get(j), orderRequest.getIdEnterprise(), orderRequest.getIdOrder()));
                }
            }
        }

        System.out.println("update: size  "+size);

        for (int i = 0; i < size; i++) {

            System.out.println("id PRODUCT:  "+orderRequest.getOrderLineProducts().get(i).getIdProduct());
            System.out.println("id Enterprise:  "+orderRequest.getIdEnterprise());
            System.out.println("id Order:  "+orderRequest.getIdOrder());
            System.out.println("id Buyer:  "+orderRequest.getIdBuyer());

            OrderLine orderLine = orderLineService.updateOrderLine(verificationDataRequest.fromOrderLineRequestToOrderLine(orderRequest.getOrderLineProducts().get(i), orderRequest.getIdEnterprise(), orderRequest.getIdOrder()));

            System.out.println("Quantity :  "+orderLine.getQuantity());
        }

        int idBuyer = orderRequest.getBuyer().getIdBuyer();
        System.out.println("idBuyer :  "+idBuyer);

        Buyer byr = buyerService.updateBuyerByID(verificationDataRequest.fromBuyerRequestToBuyer(orderRequest.getBuyer()),orderRequest.getBuyer().getIdBuyer());
        System.out.println("buyer new :  "+byr.getIdBuyer());

        orderRequest.setIdBuyer(idBuyer);

        orderRequest.setOrderTotal((float) verificationDataRequest.calculateTotalPrice(orderRequest,orderRequest.getIdEnterprise()));
        orderRequest.setTotalOutsideTax(orderRequest.getOrderTotal() + orderRequest.getTva());

        System.out.println("Enterprise id :  " +orderRequest.getIdEnterprise());
        System.out.println("ORDER TVA :  " +orderRequest.getTva());
        System.out.println("ORDER TOTAL :  " +orderRequest.getOrderTotal());
        System.out.println("ORDER TAX :  " +orderRequest.getTotalOutsideTax());

        OrderPay op = orderService.updateOrder(verificationDataRequest.fromOrderRequestToOrder(orderRequest));

        System.out.println("orderPay new :  "+op.getIdOrder());
        System.out.println("buyer new :  "+op.getIdBuyer());

        if(op!=null)
            return true;
         else
            return false;
    }

    ///////// ----------------------- Delete Order ------------------------/////////////////////////////

    @DeleteMapping(path ="/deleteOrder")
    public boolean deleteOrder(@RequestBody OrderRequest orderRequest){
        OrderPay orderPay = orderService.findOrderByIdOrder(orderRequest.getIdOrder());
        List<OrderLine> orderLines = orderLineService.findAllByIdOrderLine(orderPay.getIdOrder());

        for (int j = 0; j < orderLines.size(); j++) {
            orderLineService.deleteOrderLineByIDComposite(new OrderLineID(orderLines.get(j).getIdOrderLine(),orderLines.get(j).getIdProduct()));
        }
            return orderService.deleteOrder(verificationDataRequest.fromOrderRequestToOrder(orderRequest));
    }

    @RequestMapping(path ="/deleteOrderByID/{idOrder}")
    public void deleteOrderByID(@PathVariable int idOrder){
        OrderPay orderPay = orderService.findOrderByIdOrder(idOrder);
        List<OrderLine> orderLines = orderLineService.findAllByIdOrderLine(orderPay.getIdOrder());

        for (int j = 0; j < orderLines.size(); j++) {
        //    orderLineService.deleteOrderLineByIDComposite(new OrderLineID(orderLines.get(j).getIdOrderLine(),orderLines.get(j).getIdProduct()));
            System.out.println(orderLines.get(j).getIdOrderLine());
            orderLineService.deleteOrderLineByIdOrderLine(orderLines.get(j).getIdOrderLine());
        }

         orderService.deleteOrderByIdOrder(idOrder);
    }

    ///////// ----------------------- Others ------------------------/////////////////////////////

    @GetMapping(path ="/changeStatusOrderByID/{idOrder}/{status}/{messageCancellation}")
    public boolean changeStatusOrderByID(@PathVariable Map <String, String> pathVarsMap){

        System.out.println("change status");

        int idOrder = Integer.parseInt(pathVarsMap.get("idOrder"));
        String status = pathVarsMap.get("status");
        String messageCancellation = pathVarsMap.get("messageCancellation");

        return verificationDataRequest.updateStatusOrder(idOrder,status,messageCancellation);
    }
}
