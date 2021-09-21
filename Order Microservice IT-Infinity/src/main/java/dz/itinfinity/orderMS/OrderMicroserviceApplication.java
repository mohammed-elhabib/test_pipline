package dz.itinfinity.orderMS;

import dz.itinfinity.orderMS.IDComposites.ProductID;
import dz.itinfinity.orderMS.Services.EntityServices.*;
import dz.itinfinity.orderMS.Services.OthersServices.VerificationDataRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.text.ParseException;


@SpringBootApplication
@EnableScheduling
public class OrderMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMicroserviceApplication.class, args);
    }

  //  @Bean
  //  CommandLineRunner initial(VerificationDataRequest verificationDataRequest,OrderService ord, ProductService prdct, OrderLineService ordrLine, BuyerService byr, EnterpriseService entrprise) throws ParseException {
//
//        ProductID productID =new ProductID("G65F65F660G52","rice",1);
//        System.out.println("hhhh "+prdct.findProductByIDComposite(productID));
//        //  OrderLineRequest orderLineRequest = new OrderLineRequest();

        //   when(productRepository.findById(productID)).thenReturn(java.util.Optional.of(product));

        // /*   @Rule
//    public ExpectedException exceptionRule = ExpectedException.none();*/
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date curDate = new Date(System.currentTimeMillis());
//        String str = formatter.format(curDate);
//
//        System.out.println();
//
//        OrderPay p =new OrderPay();
//       // p.setIdOrder(17);
//        p.setIdEnterprise(2);
//        p.setIdBuyer(3);
//
//        p.setOrderNumber("hhhhhhhhhhhhhhh");
//     //   System.out.println(HelperService.getValidFormatDate("2021-09-14 10:41:11") +"ggggggggggggggg");
//
//        p.setDateOrder(HelperService.getValidFormatDate("2021-09-14 10:41:11"));
//        p.setOrderStatus(OrderStatus.notConfirm);
//        p.setOrderTotal(78554);
//
//        p.setTotalOutsideTax(88888888);
//        p.setTva(777);
//        p.setCancellationReason("");
//
//
//        System.out.println("0 ----"+ordrLine.findAllByIdOrder(1).size());
//
//      //  System.out.println("1 ----"+(new VerificationDataRequest()).orderLineProductsFromOrderLine(1));
//
//        p.setOrderStatus(OrderStatus.initial);
// 6778976e

        // /*    Product product = new Product();
        //
        //        product.setIdEnterprise(1);
        //        product.setIdProduct(1);
        //        product.setProductImage("https://www.world-grain.com/ext/resources/Article-Images/2020/12/GMR_Rice_AdobeStock_64819529_E_Nov.jpg?1609338918");
        //        product.setProductDescription("goood product1 .. bla bla bla bla");
        //        product.setProductName("rice");
        //        product.setQuantityStock(155);
        //        product.setProductRef("G65F65F660G52");
        //        product.setCategory("CA3");
        //        product.setPrice(400);
        //        product.setUnit("1 kg");*/
//
//        Product product = new Product();
//
//        product.setIdEnterprise(1);
//        product.setIdProduct(1);
//        product.setProductImage("https://www.world-grain.com/ext/resources/Article-Images/2020/12/GMR_Rice_AdobeStock_64819529_E_Nov.jpg?1609338918");
//        product.setProductDescription("goood product1 .. bla bla bla bla");
//        product.setProductName("rice");
//        product.setQuantityStock(155);
//        product.setProductRef("G65F65F660G52");
//        product.setCategory("CA3");
//        product.setPrice(400);
//        product.setUnit("1 kg");
//
//        ProductRequest productRequest =new ProductRequest();
//
//        productRequest.setProductRef("G65F65F660G52");
//        productRequest.setProductName("rice");
//
//        System.out.println("1 "+verificationDataRequest.getExistProduct(productRequest,1));
//        System.out.println("1 "+prdct.findProductByIDComposite(new ProductID(productRequest.getProductRef(),productRequest.getProductName(),1)));
     //   System.out.println("delete  "); ordrLine.deleteOrderLineByIdOrderLine(194);
//        System.out.println("2 "+ord.addOrder(p).getIdOrder());
//            System.out.println(entrprise.findEnterpriseByIDComposite(new EnterpriseID("IT_Techno","ElMansoura")).getEnterpriseName());
//        //    System.out.println(byr.findBuyerByIDComposite(new BuyerID("Sahar","aggab",HelperService.getValidFormatDate("1995-05-11 10:41:11"))).getFirstNameB());

//        return args -> {
//            ord.findAllOrders();
//        };
//
//    }
}