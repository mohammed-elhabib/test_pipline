package dz.itinfinity.orderMS.TestUnit;

import dz.itinfinity.orderMS.Entities.TablesEnt.*;
import dz.itinfinity.orderMS.IDComposites.*;
import dz.itinfinity.orderMS.Repositories.ProductRepository;
import dz.itinfinity.orderMS.Requests.*;
import dz.itinfinity.orderMS.Services.EntityServices.*;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestUnit_OrderMS_VerificationData {

    @InjectMocks
    VerificationDataRequest verificationDataRequest;

    @Mock
    private ProductService productService;
    @Mock
    private EnterpriseService enterpriseService;
    @Mock
    private BuyerService buyerService;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderLineService orderLineService;
    @Mock
    private Product product;
    @Mock
    private Buyer buyer;
    @Mock
    private Enterprise enterprise;
    @Mock
    private OrderPay order;
    @Mock
    private OrderLine orderLine;
    @Mock
    private List<OrderLine> orderLineList;

    @Before
    public void initial(){
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void getExistProduct_Test_Product() {
        ProductRequest productRequest =new ProductRequest();
        int idEnterpriseRequest = 1;
        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        ProductID productID =new ProductID(productRequest.getProductRef(),productRequest.getProductName(),idEnterpriseRequest);
        when(productService.findProductByIDComposite(productID)).thenReturn(product);
        assertEquals(product, verificationDataRequest.getExistProduct(productRequest,idEnterpriseRequest));
    }
    @Test
    public void getExistProduct_Test_Null() {
        ProductRequest productRequest =new ProductRequest();
        int idEnterpriseRequest = 2;
        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        ProductID productID =new ProductID(productRequest.getProductRef(),productRequest.getProductName(),idEnterpriseRequest);
        when(productService.findProductByIDComposite(productID)).thenReturn(null);
        assertEquals(null, verificationDataRequest.getExistProduct(productRequest,idEnterpriseRequest));
    }

    @Test
    public void getExistBuyer_Test_Buyer() {
        BuyerRequest buyerRequest = new BuyerRequest();
        buyerRequest.setFirstNameB("sahar");
        buyerRequest.setLastNameB("aggab");
        buyerRequest.setBirthDayB(new Date(19950511));

        BuyerID buyerID =new BuyerID(buyerRequest.getFirstNameB(),buyerRequest.getLastNameB(),buyerRequest.getBirthDayB());
        when(buyerService.findBuyerByIDComposite(buyerID)).thenReturn(buyer);
        assertEquals(buyer, verificationDataRequest.getExistBuyer(buyerRequest));
    }
    @Test
    public void getExistBuyer_Test_Null() {
        BuyerRequest buyerRequest = new BuyerRequest();
        buyerRequest.setFirstNameB("sahar");
        buyerRequest.setLastNameB("aggab");
        buyerRequest.setBirthDayB(new Date(1995,05,11));

        BuyerID buyerID =new BuyerID(buyerRequest.getFirstNameB(),buyerRequest.getLastNameB(),buyerRequest.getBirthDayB());
        when(buyerService.findBuyerByIDComposite(buyerID)).thenReturn(null);
        assertEquals(null, verificationDataRequest.getExistBuyer(buyerRequest));
    }

    @Test
    public void getExistEnterprise_Test_Enterprise(){
        EnterpriseRequest enterpriseRequest = new EnterpriseRequest();
        enterpriseRequest.setEnterpriseName("IT_Techno");
        enterpriseRequest.setTown("ElMansoura");

        EnterpriseID enterpriseID = new EnterpriseID(enterpriseRequest.getEnterpriseName(),enterpriseRequest.getTown());
        when(enterpriseService.findEnterpriseByIDComposite(enterpriseID)).thenReturn(enterprise);
        assertEquals(enterprise, verificationDataRequest.getExistEnterprise(enterpriseRequest));
    }
    @Test
    public void getExistEnterprise_Test_Null(){
        EnterpriseRequest enterpriseRequest = new EnterpriseRequest();
        enterpriseRequest.setEnterpriseName("IT_Techno");
        enterpriseRequest.setTown("ElMansoura");

        EnterpriseID enterpriseID = new EnterpriseID(enterpriseRequest.getEnterpriseName(),enterpriseRequest.getTown());
        when(enterpriseService.findEnterpriseByIDComposite(enterpriseID)).thenReturn(null);
        assertEquals(null, verificationDataRequest.getExistEnterprise(enterpriseRequest));
    }

    @Test
    public void getExistOrder_Test_Order(){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdOrder(57);
        orderRequest.setIdEnterprise(1);

        OrderID orderID = new OrderID(orderRequest.getIdOrder(),orderRequest.getIdEnterprise());
        when(orderService.findOrderByIDComposite(orderID)).thenReturn(order);
        assertEquals(order, verificationDataRequest.getExistOrder(orderRequest));
    }
    @Test
    public void getExistOrder_Test_Null(){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdOrder(51);
        orderRequest.setIdEnterprise(1);

        OrderID orderID = new OrderID(orderRequest.getIdOrder(),orderRequest.getIdEnterprise());
        when(orderService.findOrderByIDComposite(orderID)).thenReturn(null);
        assertEquals(null, verificationDataRequest.getExistOrder(orderRequest));
    }

    @Test
    public void getExistOrderLine_Test_OrderLine(){

        int idOrder = 1;
        int idProduct = 1;

        OrderLineID orderLineID = new OrderLineID(idOrder,idProduct);
        when(orderLineService.findOrderLineByIDComposite(orderLineID)).thenReturn(orderLine);
        assertEquals(orderLine, verificationDataRequest.getExistOrderLine(idOrder,idProduct));
    }
    @Test
    public void getExistOrderLine_Test_Null(){

        int idOrder = 1;
        int idProduct = 1;

        OrderLineID orderLineID = new OrderLineID(idOrder,idProduct);
        when(orderLineService.findOrderLineByIDComposite(orderLineID)).thenReturn(null);
        assertEquals(null, verificationDataRequest.getExistOrderLine(idOrder,idProduct));
    }

    @Test
    public void isAddListOfOrderLine_Test_True(){
        int idOrder = 31;
        int idEnterprise= 1;

        Product prod = new Product();
        prod.setIdProduct(1);

        List<ProductRequest> productRequestList = new ArrayList<>();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(5);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);

        ProductID productID =new ProductID(productRequest.getProductRef(),productRequest.getProductName(),idEnterprise);

        //   when(verificationDataRequest.getExistProduct(productRequest,idEnterprise)).thenReturn(prod);
        //   when(prod.getIdProduct()).thenReturn(1); productService

        when(productService.findProductByIDComposite(productID)).thenReturn(prod);
        when(orderLineService.addOrderLine(orderLine)).thenReturn(orderLine);
        assertEquals(true, verificationDataRequest.isAddListOfOrderLine(productRequestList,idOrder,idEnterprise));
    }
    @Test
    public void isAddListOfOrderLine_Test_False(){
        int idOrder = 31;
        int idEnterprise= 1;

        Product prod = new Product();
        prod.setIdProduct(1);

        List<ProductRequest> productRequestList = new ArrayList<>();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(5);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);

        ProductID productID = new ProductID(productRequest.getProductRef(),productRequest.getProductName(),idEnterprise);

        //   when(verificationDataRequest.getExistProduct(productRequest,idEnterprise)).thenReturn(prod);
        //   when(prod.getIdProduct()).thenReturn(1); productService

        when(productService.findProductByIDComposite(productID)).thenReturn(prod);
        when(orderLineService.addOrderLine(orderLine)).thenReturn(orderLine);
        assertEquals(false, verificationDataRequest.isAddListOfOrderLine(productRequestList,idOrder,idEnterprise));
    }

    @Test
    public void reduceQuantityStock_Test_Int(){
        int idOrder = 31;

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);

        enterprise.setReservationTime(5);
        when(orderLineService.findAllByIdOrderLine(idOrder)).thenReturn(orderLineList);
        when(productService.findProductByID(1)).thenReturn(prod);
        when(productService.updateProduct(prod)).thenReturn(prod);
        when(enterpriseService.findEnterpriseByID(1)).thenReturn(enterprise);

        assertEquals(5, verificationDataRequest.reduceQuantityStock(idOrder));
    }

    @Test
    public void isCorrectTotalPrice_Test_True(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(true, verificationDataRequest.isCorrectTotalPrice(orderRequest,idEnterprise));
    }
    @Test
    public void isCorrectTotalPrice_Test_Exception(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(exceptionRule, verificationDataRequest.isCorrectTotalPrice(orderRequest,idEnterprise));
    }

    @Test
    public void calculateTotalPrice_Test_Double(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(false, verificationDataRequest.calculateTotalPrice(orderRequest,1));
    }

    @Test
    public void isAvailableQuantity_Test_True(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(true, verificationDataRequest.isAvailableQuantity(productRequestList,idEnterprise));
    }
    @Test
    public void isAvailableQuantity_Test_Exception(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(exceptionRule, verificationDataRequest.isAvailableQuantity(productRequestList,idEnterprise));
    }

    @Test
    public void isOrderLineProductsExist_Test_True(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(true, verificationDataRequest.isOrderLineProductsExist(productRequestList,idEnterprise));
    }
    @Test
    public void isOrderLineProductsExist_Test_Exception(){
        int idEnterprise = 1;

        List<ProductRequest> productRequestList = new ArrayList<>();

        OrderRequest orderRequest = new OrderRequest();
        ProductRequest productRequest =new ProductRequest();

        productRequest.setProductRef("G65F65F660G52");
        productRequest.setProductName("rice");

        productRequest.setIdProduct(1);
        productRequest.setQuantity(10);
        productRequest.setTotal(3920);
        productRequest.setDiscount(2);

        productRequestList.add(productRequest);
        orderRequest.setOrderLineProducts(productRequestList);

        Product prod = new Product();
        prod.setIdEnterprise(1);
        prod.setIdProduct(1);
        prod.setQuantityStock(155);
        prod.setPrice(400);

        when(orderRequest.getTva()).thenReturn(120.0F);
        assertEquals(exceptionRule, verificationDataRequest.isOrderLineProductsExist(productRequestList,idEnterprise));
    }

    @Test
    public void updateStatusOrder_Test_True(){
        assertEquals(true, verificationDataRequest.updateStatusOrder(31,"cancel", ""));
    }
    @Test
    public void updateStatusOrder_Test_False(){
        assertEquals(false, verificationDataRequest.updateStatusOrder(31,"cancel", ""));
    }

    @Test
    public void updateAddressBuyer_Test_Buyer(){
        assertEquals(buyer, verificationDataRequest.updateAddressBuyer(21,11));
    }
    @Test
    public void updateAddressBuyer_Test_Null(){
        assertEquals(null, verificationDataRequest.updateAddressBuyer(61,11));
    }

}
