package dz.itinfinity.orderMS.Services.OthersServices;

import dz.itinfinity.orderMS.Entities.Enums.OrderStatus;
import dz.itinfinity.orderMS.Entities.TablesEnt.*;
import dz.itinfinity.orderMS.Exceptions.EntityNotFoundExceptions.ProductNotFoundException;
import dz.itinfinity.orderMS.Exceptions.InputValidationException.InputIncorrectResultCountException;
import dz.itinfinity.orderMS.IDComposites.*;
import dz.itinfinity.orderMS.Services.EntityServices.*;
import dz.itinfinity.orderMS.Requests.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class VerificationDataRequest {

    public   final Logger log = LoggerFactory.getLogger(VerificationDataRequest.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderLineService orderLineService;

    private Product product = new Product();
    private Enterprise enterprise = new Enterprise();
    private Buyer buyer = new Buyer();
    private OrderLine orderLine = new OrderLine();
    private OrderPay order = new OrderPay();

    public VerificationDataRequest(){ }

// ------------------------------------- Product By ID Composite ------------------------------------------------------------

    public Product getExistProduct(ProductRequest productRequest, int idEnterpriseRequest) {
        log.info("- Start, get entity product if exist ...");

        ProductID productID =new ProductID(productRequest.getProductRef(),productRequest.getProductName(),idEnterpriseRequest);
        product = productService.findProductByIDComposite(productID);

        if (product==null)
            log.debug("*** This product is not found ***");
        else
            log.debug("*** This product is found ***");

        return product;
    }

    public ProductRequest fromProductToProductRequest(Product product) {
        log.info("- Start, from product request to entity product ...");

        ProductRequest productRequest = new ProductRequest();

        productRequest.setIdProduct(product.getIdProduct());
        productRequest.setProductImage(product.getProductImage());
        productRequest.setProductDescription(product.getProductDescription());
        productRequest.setProductName(product.getProductName());
        productRequest.setProductRef(product.getProductRef());
        productRequest.setCategory(product.getCategory());
        productRequest.setPrice(product.getPrice());
        productRequest.setUnit(product.getUnit());

        log.debug("*** This product is formed ***");

        return productRequest;
    }

    public Product fromProductRequestToProduct(ProductRequest productRequest, int idEnterpriseRequest) {
        log.info("- Start, from product entity to product request ...");

            product.setIdEnterprise(idEnterpriseRequest);

            product.setIdProduct(productRequest.getIdProduct());
            product.setProductImage(productRequest.getProductImage());
            product.setProductDescription(productRequest.getProductDescription());
            product.setProductName(productRequest.getProductName());
            product.setProductRef(productRequest.getProductRef());
            product.setCategory(productRequest.getCategory());
            product.setPrice(productRequest.getPrice());
            product.setUnit(productRequest.getUnit());

            log.debug("*** This product is formed ***");

        return product;

    }

// ------------------------------------- Buyer By ID Composite --------------------------------------------------------------

    public Buyer getExistBuyer(BuyerRequest buyerRequest) {
        log.info("- Start, get entity buyer if exist ...");

    //            buyer.setIdBuyer(buyerRequest.getIdBuyer());
    //            buyer.setFirstNameB(buyerRequest.getFirstNameB());
    //            buyer.setLastNameB(buyerRequest.getLastNameB());
    //            buyer.setBirthDayB(buyerRequest.getBirthDayB());
    //            buyer.setEmailBuyer(buyerRequest.getEmailBuyer());
    //            buyer.setPhoneBuyer(buyerRequest.getPhoneBuyer());
    //            buyer.setBuyerStatus(buyerRequest.getBuyerStatus());
    //            buyer.setRegisterCommercialNumberB(buyerRequest.getRegisterCommercialNumberB());
    //            buyer.setTaxIdentificationNumberB(buyerRequest.getTaxIdentificationNumberB());
    //            buyer.setWebSiteB(buyerRequest.getWebSiteB());
    //            buyer.setArticleBuyer(buyerRequest.getArticleBuyer());
    //            buyer.setEnterpriseNameBuyer(buyerRequest.getEnterpriseNameBuyer());

        BuyerID buyerID =new BuyerID(buyerRequest.getFirstNameB(),buyerRequest.getLastNameB(),buyerRequest.getBirthDayB());
        buyer = buyerService.findBuyerByIDComposite(buyerID);

        if (buyer==null)
            log.debug("*** This buyer is not found ***");
        else
            log.debug("*** This buyer is found ***");

        return buyer;
    }

    public Buyer fromBuyerRequestToBuyer(BuyerRequest buyerRequest) {

        buyer.setFirstNameB(buyerRequest.getFirstNameB());
        buyer.setLastNameB(buyerRequest.getLastNameB());
        buyer.setBirthDayB(buyerRequest.getBirthDayB());
        buyer.setEmailBuyer(buyerRequest.getEmailBuyer());
        buyer.setPhoneBuyer(buyerRequest.getPhoneBuyer());
        buyer.setBuyerStatus(buyerRequest.getBuyerStatus());
        buyer.setRegisterCommercialNumberB(buyerRequest.getRegisterCommercialNumberB());
        buyer.setTaxIdentificationNumberB(buyerRequest.getTaxIdentificationNumberB());
        buyer.setWebSiteB(buyerRequest.getWebSiteB());
        buyer.setArticleBuyer(buyerRequest.getArticleBuyer());
        buyer.setEnterpriseNameBuyer(buyerRequest.getEnterpriseNameBuyer());
        // buyer.setIdAddress(1);

        return buyer;
    }

    public BuyerRequest fromBuyerToBuyerRequest(Buyer buyer) {

        BuyerRequest buyerRequest =new BuyerRequest();

        buyerRequest.setFirstNameB(buyer.getFirstNameB());
        buyerRequest.setLastNameB(buyer.getLastNameB());
        buyerRequest.setBirthDayB(buyer.getBirthDayB());
        buyerRequest.setEmailBuyer(buyer.getEmailBuyer());
        buyerRequest.setPhoneBuyer(buyer.getPhoneBuyer());
        buyerRequest.setBuyerStatus(buyer.getBuyerStatus());
        buyerRequest.setRegisterCommercialNumberB(buyer.getRegisterCommercialNumberB());
        buyerRequest.setTaxIdentificationNumberB(buyer.getTaxIdentificationNumberB());
        buyerRequest.setWebSiteB(buyer.getWebSiteB());
        buyerRequest.setArticleBuyer(buyer.getArticleBuyer());
        buyerRequest.setEnterpriseNameBuyer(buyer.getEnterpriseNameBuyer());
        // buyer.setIdAddress(1);

        return buyerRequest;
    }

//// ------------------------------------- Enterprise by IdEnterprise --------------------------------------------------------

    public Enterprise getExistEnterprise(EnterpriseRequest enterpriseRequest) {
        log.info("- Start, get entity enterprise if exist ...");
//
//        enterprise.setIdEnterprise(enterpriseRequest.getIdEnterprise());
//        enterprise.setEnterpriseName(enterpriseRequest.getEnterpriseName());
//        enterprise.setTown(enterpriseRequest.getTown());
//        enterprise.setWebSite(enterpriseRequest.getWebSite());
//        enterprise.setTransportCosts(enterpriseRequest.getTransportCosts());
//        enterprise.setAccountNumber(enterpriseRequest.getAccountNumber());
//        enterprise.setAccountType(enterpriseRequest.getAccountType());
//        enterprise.setLogoImage(enterpriseRequest.getLogoImage());
//        enterprise.setAcceptationTime(enterpriseRequest.getAcceptationTime());
//        enterprise.setReservationTime(enterpriseRequest.getReservationTime());
//        enterprise.setValidationTime(enterpriseRequest.getValidationTime());
//        enterprise.setOrderRecoveryTime(enterpriseRequest.getOrderRecoveryTime());
//        enterprise.setDeliveryTime(enterpriseRequest.getDeliveryTime());
//        enterprise.setArticleSeller(enterpriseRequest.getArticleSeller());
//        enterprise.setRegisterCommercialNumberS(enterpriseRequest.getRegisterCommercialNumberS());
//        enterprise.setTaxIdentificationNumberS(enterpriseRequest.getTaxIdentificationNumberS());
//        enterprise.setPaymentMethod(HelperService.getChoiceEnum(enterpriseRequest.getPaymentMethod()));
//        enterprise.setPublicPrivate(enterpriseRequest.getPublicPrivate());


        enterprise = enterpriseService.findEnterpriseByIDComposite(new EnterpriseID(enterpriseRequest.getEnterpriseName(),enterpriseRequest.getTown()));

        if(enterprise !=null)
            log.debug("*** enterprise is found ***");
        else
            log.debug("*** enterprise is not found ***");

        return enterprise;
    }

    public EnterpriseRequest fromEnterpriseToEnterpriseRequest(Enterprise enterprise) {
        log.info("- Start, build entity enterprise request from enterprise ...");
        EnterpriseRequest enterpriseRequest = new EnterpriseRequest();

        enterpriseRequest.setIdEnterprise(enterprise.getIdEnterprise());
        enterpriseRequest.setEnterpriseName(enterprise.getEnterpriseName());
        enterpriseRequest.setTown(enterprise.getTown());
        enterpriseRequest.setWebSite(enterprise.getWebSite());
        enterpriseRequest.setTransportCosts(enterprise.getTransportCosts());
        enterpriseRequest.setAccountNumber(enterprise.getAccountNumber());
        enterpriseRequest.setAccountType(enterprise.getAccountType());
        enterpriseRequest.setLogoImage(enterprise.getLogoImage());
        enterpriseRequest.setAcceptationTime(enterprise.getAcceptationTime());
        enterpriseRequest.setReservationTime(enterprise.getReservationTime());
        enterpriseRequest.setValidationTime(enterprise.getValidationTime());
        enterpriseRequest.setOrderRecoveryTime(enterprise.getOrderRecoveryTime());
        enterpriseRequest.setDeliveryTime(enterprise.getDeliveryTime());
        enterpriseRequest.setArticleSeller(enterprise.getArticleSeller());
        enterpriseRequest.setRegisterCommercialNumberS(enterprise.getRegisterCommercialNumberS());
        enterpriseRequest.setTaxIdentificationNumberS(enterprise.getTaxIdentificationNumberS());
        enterpriseRequest.setPaymentMethod(HelperService.getChoiceEnumList(enterprise.getPaymentMethod()));
        enterpriseRequest.setPublicPrivate(enterprise.getPublicPrivate());

        log.debug("*** enterprise request is build ***");
        return enterpriseRequest;
    }

    public Enterprise fromEnterpriseRequestToEnterprise(EnterpriseRequest enterpriseRequest) {
        log.info("- Start, build entity enterprise from enterprise request ...");

        enterprise.setIdEnterprise(enterpriseRequest.getIdEnterprise());
        enterprise.setEnterpriseName(enterpriseRequest.getEnterpriseName());
        enterprise.setTown(enterpriseRequest.getTown());
        enterprise.setWebSite(enterpriseRequest.getWebSite());
        enterprise.setTransportCosts(enterpriseRequest.getTransportCosts());
        enterprise.setAccountNumber(enterpriseRequest.getAccountNumber());
        enterprise.setAccountType(enterpriseRequest.getAccountType());
        enterprise.setLogoImage(enterpriseRequest.getLogoImage());
        enterprise.setAcceptationTime(enterpriseRequest.getAcceptationTime());
        enterprise.setReservationTime(enterpriseRequest.getReservationTime());
        enterprise.setValidationTime(enterpriseRequest.getValidationTime());
        enterprise.setOrderRecoveryTime(enterpriseRequest.getOrderRecoveryTime());
        enterprise.setDeliveryTime(enterpriseRequest.getDeliveryTime());
        enterprise.setArticleSeller(enterpriseRequest.getArticleSeller());
        enterprise.setRegisterCommercialNumberS(enterpriseRequest.getRegisterCommercialNumberS());
        enterprise.setTaxIdentificationNumberS(enterpriseRequest.getTaxIdentificationNumberS());
        enterprise.setPaymentMethod(HelperService.getChoiceEnum(enterpriseRequest.getPaymentMethod()));
        enterprise.setPublicPrivate(enterpriseRequest.getPublicPrivate());

        log.debug("*** enterprise build ***");
        return enterprise;
    }

//// ------------------------------------- Order by IdOrder --------------------------------------------------------------

    //    public List<OrderLine> fromOrderLineRequestToOrderLine(List<ProductRequest> productRequests ,int idOrder) {
//        log.info("- Start, get entity order line if exist ...");
//
//        List<OrderLine> orderLineList = new ArrayList<>();
//        Product product = null;
//
//        System.out.println(orderLineService.findAllByIdOrder(idOrder));
//
//        List<OrderLine> orderLineList = new ArrayList<>();
//        orderLineList = orderLineService.findAllByIdOrder(idOrder);
//
//
//        for (int i = 0; i < productRequests.size(); i++) {
//
//            product = productService.findProductByID(orderLineList.get(i).getIdProduct());
//            System.out.println(product.getIdProduct());
//
//            productRequests.get(i).setIdProduct(product.getIdProduct());
//            productRequests.get(i).setProductImage(product.getProductImage());
//            productRequests.get(i).setProductDescription(product.getProductDescription());
//            productRequests.get(i).setProductName(product.getProductName());
//            productRequests.get(i).setProductRef(product.getProductRef());
//            productRequests.get(i).setCategory(product.getCategory());
//            productRequests.get(i).setPrice(product.getPrice());
//            productRequests.get(i).setUnit(product.getUnit());
//
//            // orderLine.setIdOrderLine(orderLineRequest.getIdOrderLine());
//            productRequests.get(i).setTotal(orderLineList.get(i).getTotal());
//            productRequests.get(i).setQuantity(orderLineList.get(i).getQuantity());
//            productRequests.get(i).setDiscount(orderLineList.get(i).getDiscount());
//
//            log.debug("*** This order line is found ***");
//            return productRequests;
//        }
//        log.trace("this order line is not found in the system DB");
//        return null;
//    }

    public OrderPay getExistOrderByIdOrder(int idOrder) {
        log.info("- Start, get entity order by ID  if exist ...");

        log.debug("*** This order is found ***");
        return orderService.findOrderByIdOrder(idOrder);
    }

//// ------------------------------------- Order by ID Composite --------------------------------------------------------------

    public OrderPay getExistOrder(OrderRequest orderRequest) {
        log.info("- Start, get entity order by composite ID  if exist ...");
        //       Timestamp dateStamp = new Timestamp(orderRequest.getDateOrder().getTime());

//        Buyer buyer = getExistBuyer(orderRequest.getBuyer());
//        Enterprise enterprise = getExistEnterprise(orderRequest.getEnterprise());

//        if (buyer != null && enterprise != null) {
//
//            order.setIdBuyer(buyer.getIdBuyer());
//            order.setIdEnterprise(enterprise.getIdEnterprise());
//            order.setIdOrder(orderRequest.getIdOrder());
//            order.setOrderNumber(orderRequest.getOrderNumber());
//            order.setOrderTotal(orderRequest.getOrderTotal());
//            order.setDateOrder(dateStamp);
//            order.setOrderStatus(orderRequest.getOrderStatus());
//            order.setTotalOutsideTax(orderRequest.getTotalOutsideTax());
//            order.setTva(orderRequest.getTva());
//            order.setCancellationReason(orderRequest.getCancellationReason());
        //  }

        OrderPay orderPay = orderService.findOrderByIDComposite(new OrderID(orderRequest.getIdOrder(),orderRequest.getIdEnterprise()));
        if(orderPay==null)
            log.warn("*** This order is not found ***");
        else
            log.debug("*** This order is found ***");

        return orderPay;
    }

    public OrderPay fromOrderRequestToOrder(OrderRequest orderRequest) {

        order = new OrderPay();

        order.setIdOrder(orderRequest.getIdOrder());
        order.setIdEnterprise(orderRequest.getIdEnterprise());
        order.setIdBuyer(orderRequest.getIdBuyer());
        order.setOrderNumber(orderRequest.getOrderNumber());

        System.out.println("ORDER TVA 1:  " +orderRequest.getTva());
        System.out.println("ORDER TOTAL 1:  " +orderRequest.getOrderTotal());
        System.out.println("ORDER TAX 1:  " +orderRequest.getTotalOutsideTax());

        order.setDateOrder(orderRequest.getDateOrder());
        order.setOrderStatus(orderRequest.getOrderStatus());

        order.setOrderTotal(orderRequest.getOrderTotal());
        order.setTotalOutsideTax(orderRequest.getTotalOutsideTax());
        order.setTva(orderRequest.getTva());

        order.setCancellationReason(orderRequest.getCancellationReason());

        System.out.println("ORDER TVA 2:  " +order.getTva());
        System.out.println("ORDER TOTAL 2:  " +order.getOrderTotal());
        System.out.println("ORDER TAX 2:  " +order.getTotalOutsideTax());

        return order;
    }

    public OrderRequest fromOrderToOrderRequest(OrderPay order) {

        OrderRequest orderRequest = new OrderRequest();
        System.out.println(order.getDateOrder());

        orderRequest.setOrderNumber(order.getOrderNumber());
        orderRequest.setIdEnterprise(order.getIdEnterprise());
        orderRequest.setIdBuyer(order.getIdBuyer());
        orderRequest.setOrderTotal(order.getOrderTotal());
        orderRequest.setDateOrder(order.getDateOrder());
        orderRequest.setOrderStatus(order.getOrderStatus());
        orderRequest.setTotalOutsideTax(order.getTotalOutsideTax());
        orderRequest.setTva(order.getTva());
        orderRequest.setCancellationReason(order.getCancellationReason());

        return orderRequest;
    }

    public Order2Request fromOrderToOrder2Request(OrderPay order) {
        Date dateStamp = new Date(order.getDateOrder().getTime());
        System.out.println("date frontend "+order.getDateOrder());

        Order2Request orderRequest = new Order2Request();

        orderRequest.setOrderNumber(order.getOrderNumber());
        orderRequest.setOrderTotal(order.getOrderTotal());
        orderRequest.setDateOrder(order.getDateOrder());
        orderRequest.setOrderStatus(order.getOrderStatus());
        orderRequest.setTotalOutsideTax(order.getTotalOutsideTax());
        orderRequest.setTva(order.getTva());
        orderRequest.setCancellationReason(order.getCancellationReason());

        System.out.println(orderRequest.getDateOrder());
        return orderRequest;
    }

//// ------------------------------------- Order Line by ID Composite --------------------------------------------------------------

    public OrderLine getExistOrderLine(int idOrder, int idProduct) {
        log.info("- Start, get entity order line if exist ... ");

        OrderLineID orderLineID = new OrderLineID(idOrder,idProduct);
        orderLine = orderLineService.findOrderLineByIDComposite(orderLineID);

            if(orderLine == null)
                log.debug("*** This order line is not found *** ");
            else
                log.debug("*** This order line is found *** ");

        return orderLine;
    }

    public OrderLine fromOrderLineRequestToOrderLine(ProductRequest productRequest, int idEnterprise, int idOrder) {
        log.info("- Start, convert order line request to entity order line ...");

        orderLine = new OrderLine();
        int idProduct= getExistProduct(productRequest,idEnterprise).getIdProduct();

        // orderLine.setIdOrderLine(orderLineRequest.getIdOrderLine());
        orderLine.setIdProduct(idProduct);
        orderLine.setIdOrder(idOrder);
        orderLine.setTotal(productRequest.getTotal());
        orderLine.setQuantity(productRequest.getQuantity());
        orderLine.setDiscount(productRequest.getDiscount());

        System.out.println("Quantity :  "+orderLine.getQuantity());

        log.debug("*** This order line is formed ***");

        return orderLine;
    }

    public boolean isAddListOfOrderLine(List<ProductRequest> productRequests, int idEnterprise, int idOrder) {
        log.info("- Start, add list entity order line ... ");

        for (int i = 0; i < productRequests.size();i++) {

            int idProduct= getExistProduct(productRequests.get(i),idEnterprise).getIdProduct();

            log.info("idProduct: "+idProduct);

            // orderLine.setIdOrderLine(orderLineRequest.getIdOrderLine());
            orderLine.setIdProduct(idProduct);
            orderLine.setIdOrder(idOrder);
            orderLine.setTotal(productRequests.get(i).getTotal());
            orderLine.setQuantity(productRequests.get(i).getQuantity());
            orderLine.setDiscount(productRequests.get(i).getDiscount());

            if(orderLineService.addOrderLine(orderLine) == null){
                log.warn("*** This order line is not added ***");
                return false;
            }
        }
        log.info("This list of order lines are added in the system DB");
        return true;
    }

    public List<ProductRequest> orderLineProductsFromOrderLine(int idOrder) {
        log.info("- Start, get entity order line if exist ...");

        List<ProductRequest> productRequests = new ArrayList<ProductRequest>();
        Product product = new Product();
        ProductRequest productRequest = new ProductRequest();

     //   System.out.println(orderLineService.findAllByIdOrderLine(idOrder));

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList = orderLineService.findAllByIdOrderLine(idOrder);

        for (int i = 0; i < orderLineList.size(); i++) {

            product = productService.findProductByID(orderLineList.get(i).getIdProduct());
            System.out.println(product.getIdProduct()+"  - "+i);

            productRequest.setIdProduct(product.getIdProduct());
            productRequest.setProductImage(product.getProductImage());
            productRequest.setProductDescription(product.getProductDescription());
            productRequest.setProductName(product.getProductName());
            productRequest.setProductRef(product.getProductRef());
            productRequest.setCategory(product.getCategory());
            productRequest.setPrice(product.getPrice());
            productRequest.setUnit(product.getUnit());

            // orderLine.setIdOrderLine(orderLineRequest.getIdOrderLine());
            productRequest.setTotal(orderLineList.get(i).getTotal());
            productRequest.setQuantity(orderLineList.get(i).getQuantity());
            productRequest.setDiscount(orderLineList.get(i).getDiscount());

            log.debug("*** This order line is added to the list request ***");
            productRequests.add(productRequest);
            productRequest =new ProductRequest();
            System.out.println(productRequests.get(i).getIdProduct()+" ("+i);
        }

        log.info("All order line is found in the system DB "+productRequests.size());
//
//        System.out.println(productRequests.get(0).getIdProduct());
//        System.out.println(productRequests.get(1).getIdProduct());
//        System.out.println(productRequests.get(2).getIdProduct());
//        System.out.println(productRequests.get(3).getIdProduct());
//        System.out.println(productRequests.get(4).getIdProduct());

        return productRequests;
    }

//// ------------------------------------- reduceQuantityStock -------------------------------------------------------------------

    public int reduceQuantityStock(int idOrder) {
        log.info("- Start, update quantity stock for product  ...");

        Product product = null;
        System.out.println(orderLineService.findAllByIdOrderLine(idOrder));

        List<OrderLine> orderLineList = new ArrayList<>();
        orderLineList = orderLineService.findAllByIdOrderLine(idOrder);

        for (int i = 0; i < orderLineList.size(); i++) {

            product = new Product();
            product = productService.findProductByID(orderLineList.get(i).getIdProduct());
            System.out.println(product.getIdProduct());
            product.setQuantityStock(product.getQuantityStock()-orderLineList.get(i).getQuantity());
            productService.updateProduct(product);

            log.debug("*** This order line is update ***");
        }
        return enterpriseService.findEnterpriseByID(product.getIdEnterprise()).getReservationTime();
    }

//// ------------------------------------- isCorrectTotalPrice -------------------------------------------------------------------

    public boolean isCorrectTotalPrice(OrderRequest orderRequest, int idEnterprise) {
        log.info("- Start, verify validation of calculate the total price of this order ...");

        float d, c, p, t, totalPrice = 0.0F;
        int q;

        for (int i = 0; i < orderRequest.getOrderLineProducts().size(); i++) {

            p = getExistProduct(orderRequest.getOrderLineProducts().get(i),idEnterprise).getPrice();
            d = orderRequest.getOrderLineProducts().get(i).getDiscount();
            q = orderRequest.getOrderLineProducts().get(i).getQuantity();
            t = orderRequest.getOrderLineProducts().get(i).getTotal();

            d = (p * q) * (d / 100);
            c = (p * q) - d;

            if (c != t) {
                log.error("The total price of this product is incorrect in calculation");
                throw new InputIncorrectResultCountException("The total price of this order line request (" + orderRequest.getDateOrder() + ") for '" + orderRequest.getOrderLineProducts().get(i).getProductName() + "' product is incorrect, the input request value: '" + t + "' not equals the calculated value: '" + c + "' !!!");
            }
            totalPrice = totalPrice + (t);
        }

        if (totalPrice != orderRequest.getTotalOutsideTax()) {
            log.error("The total price of product list without tax is incorrect in calculation");
            throw new InputIncorrectResultCountException("The total price without Tax '" + orderRequest.getTva() + "' of this order request (" + orderRequest.getDateOrder() + ") is incorrect, the input request value: '" + orderRequest.getTotalOutsideTax() + "' not equals the calculated value: '" + totalPrice + "' !!!");
        }

        if ((totalPrice + orderRequest.getTva()) != orderRequest.getOrderTotal()) {
            log.error("The total price of product list with tax is incorrect in calculation");
            throw new InputIncorrectResultCountException("The total price with Tax '" + orderRequest.getTva() + "' of this order request (" + orderRequest.getDateOrder() + ") is incorrect, the input request value: '" + orderRequest.getOrderTotal() + "' not equals the calculated value: '" + (totalPrice + orderRequest.getTva()) + "' !!!");
        }

        log.debug("*** The total price of product list is correct ***");
        return true;
    }

//// ------------------------------------- isAvailableQuantity ------------------------------------------------------------------

    public boolean isAvailableQuantity(List<ProductRequest> productRequests, int idEnterprise) {
        log.info("- Start, verify validation availability of The stock quantity ...");

        int q, qs ;
        for (int i = 0; i < productRequests.size(); i++) {

            q = productRequests.get(i).getQuantity();
            qs = getExistProduct(productRequests.get(i), idEnterprise).getQuantityStock();

            if (qs < q) {
                log.error("this The stock quantity of this product is not enough ");
                throw new InputIncorrectResultCountException("The quantity of stock is not enough from enterprise ID: (" + idEnterprise + ") in '" + productRequests.get(i).getProductName() + "' product, the quantity required value: '" + q + "' || the quantity of stock value: '" + qs + "' !!!");
            }
        }
        log.debug("*** The quantity of stock is available ***");
        return true;
    }

//// ------------------------------------- isOrderLineProductsExist --------------------------------------------------------------

    public boolean isOrderLineProductsExist(List<ProductRequest> productRequests, int idEnterprise) {
        log.info("- Start, verify validation existent of the list of product ...");

        for (int i = 0; i < productRequests.size(); i++) {

            if(getExistProduct(productRequests.get(i), idEnterprise) == null){
                log.error("this product by this information not exist in the system DB");
                throw new ProductNotFoundException("This product:'" + productRequests.get(i).getProductName() + "' from enterprise ID: (" + idEnterprise +") is not found!!!");
            }
        }
        log.debug("*** This list of product is exist ***");
        return true;
    }

//// ------------------------------------- updateStatusOrder --------------------------------------------------------------

    public boolean updateStatusOrder(int idOrder, String status, String messageCancellation){

        log.info("start function update status order");
        int reservationTime;

        if(OrderStatus.reservationRequest.toString().equalsIgnoreCase(status)){
            log.info("request status order to "+ status+" for order id: "+idOrder);

            if (orderService.findOrderByIdOrder(idOrder).getOrderStatus() == OrderStatus.notConfirm){
                log.debug("reservation request update is done");
                        return orderService.updateStatusOrder(idOrder,(OrderStatus) HelperService.getEnumItemChoice("OrderStatus", status));
                }
        }
        if(OrderStatus.reserved.toString().equalsIgnoreCase(status)){
            log.info("request changing status order to "+ status+" for order id: "+idOrder);

            if(orderService.findOrderByIdOrder(idOrder).getOrderStatus() == OrderStatus.reservationRequest) {
                log.debug("reserved update is done");
                reservationTime = reduceQuantityStock(idOrder);
                log.info("reservation time "+ reservationTime);
                return orderService.updateStatusOrder(idOrder,(OrderStatus) HelperService.getEnumItemChoice("OrderStatus", status));
            }
        }
        if(OrderStatus.cancel.toString().equalsIgnoreCase(status)){
            log.info("request changing status order to "+ status+" for order id: "+idOrder+" by cause: "+messageCancellation);

            if (orderService.findOrderByIdOrder(idOrder).getOrderStatus() != OrderStatus.paid && orderService.findOrderByIdOrder(idOrder).getOrderStatus() != OrderStatus.initial && orderService.findOrderByIdOrder(idOrder).getOrderStatus() != OrderStatus.cancel){
                log.debug("cancel update is done");
                return orderService.updateStatusOrderCancel(idOrder,(OrderStatus) HelperService.getEnumItemChoice("OrderStatus", status),messageCancellation);
            }
        }
        if(OrderStatus.paid.toString().equalsIgnoreCase(status)){
            log.info("request changing status order to "+ status+" for order id: "+idOrder);

            if (orderService.findOrderByIdOrder(idOrder).getOrderStatus() == OrderStatus.notConfirm || orderService.findOrderByIdOrder(idOrder).getOrderStatus() == OrderStatus.reserved) {
                log.debug("paid update is done");
                return orderService.updateStatusOrder(idOrder, (OrderStatus) HelperService.getEnumItemChoice("OrderStatus", status));
            }
        }
        return false;
    }

//// ------------------------------------- calculateTotalPrice --------------------------------------------------------------

    public double calculateTotalPrice(OrderRequest orderRequest, int idEnterprise) {

            float d, p, t, totalPrice = 0.0F;
            int q;

            for (int i = 0; i < orderRequest.getOrderLineProducts().size(); i++) {

                p = getExistProduct(orderRequest.getOrderLineProducts().get(i), idEnterprise).getPrice();

                d = orderRequest.getOrderLineProducts().get(i).getDiscount();
                q = orderRequest.getOrderLineProducts().get(i).getQuantity();
            //  t = orderRequest.getOrderLineProducts().get(i).getTotal();

                d = (p * q) * (d / 100);
                t = (p * q) - d;

                totalPrice = totalPrice + (t);
            }

            return totalPrice;
    }

//// ------------------------------------- updateAddressBuyer --------------------------------------------------------------

    public Buyer updateAddressBuyer(int idBuyer, int idAddress) {

        buyer = buyerService.findBuyerByID(idBuyer);
        buyer.setIdAddress(idAddress);
        buyer = buyerService.updateBuyer(buyer);

        if(buyer==null)
            log.warn("update address is not done");
        else
            log.info("update address is done");

        return buyer;
    }

////////////////////////////////////////// -------- Others--------- /////////////////////////////////////////////////////

}