package dz.itinfinity.orderMS;

import dz.itinfinity.orderMS.Entities.TablesEnt.Buyer;
import dz.itinfinity.orderMS.Entities.TablesEnt.Product;
import dz.itinfinity.orderMS.IDComposites.ProductID;
import dz.itinfinity.orderMS.Requests.BuyerRequest;
import dz.itinfinity.orderMS.Requests.OrderRequest;
import dz.itinfinity.orderMS.Requests.ProductRequest;
import dz.itinfinity.orderMS.Services.EntityServices.*;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderMicroserviceApplicationTests {

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
    ProductID productID ;
    @Mock
    OrderRequest orderRequest;
    @Mock
    BuyerRequest buyerRequest;
  //  @Mock

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


        @Before
        public void init() throws ParseException {

            MockitoAnnotations.initMocks(this);
        }
        @Rule
        public ExpectedException exceptionRule = ExpectedException.none();


        @Test
        public void getExistProduct_Test_Product() throws Exception {
            ProductRequest productRequest =new ProductRequest();

            productRequest.setProductRef("G65F65F660G52");
            productRequest.setProductName("rice");

            Product product = new Product();

            product.setIdProduct(1);
            product.setProductImage("https://www.world-grain.com/ext/resources/Article-Images/2020/12/GMR_Rice_AdobeStock_64819529_E_Nov.jpg?1609338918");
            product.setProductDescription("goood product1 .. bla bla bla bla");
            product.setProductName("rice");
            product.setQuantityStock(155);
            product.setProductRef("G65F65F660G52");
            product.setCategory("CA3");
            product.setPrice(400);
            product.setUnit("1 kg");

            when(productService.findProductByIDComposite(productID)).thenReturn(product);
            assertEquals(true, verificationDataRequest.getExistProduct(productRequest,1));
        }

}
