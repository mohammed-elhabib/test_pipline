package com.dz.itinfinity.backend_payment.services;

import com.dz.itinfinity.backend_payment.domain.DTOs.*;
import com.dz.itinfinity.backend_payment.domain.enums.BuyerStatus;
import com.dz.itinfinity.backend_payment.domain.enums.DeliveryStatus;
import com.dz.itinfinity.backend_payment.domain.enums.DeliveryType;
import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import com.dz.itinfinity.backend_payment.exceptions.BuyerIdNullException;
import com.dz.itinfinity.backend_payment.exceptions.OrderIdNullException;
import com.dz.itinfinity.backend_payment.exceptions.SellerIdNullException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentOverviewService {
    Logger logger = LoggerFactory.getLogger(PaymentOverviewService.class);

    @Autowired
    RestTemplate restTemplate;

    public OrderDto getOrderOverview(Integer orderId) {
        if (orderId == null) {
            throw new OrderIdNullException("order id must be not null ");
        }
        logger.info("get from order microservice order info");
     //   OrderResponse orderResponse= restTemplate.getForObject("http://localhost:99/getOrder/{"+orderId+"}",OrderResponse.class);

        return OrderDto.builder()
                .idOrder(5)
                .orderNumber(165155)
                .orderTotal(100000)
                .totalOutsideTax(100020)
                .tva(100600).orderStatus("success").idBuyer(5).idEnterprise(5).
                        build();
    }

    public DeliveryDto getDeliveryOverview(Integer deliveryId) {
        if (deliveryId == null) {
            throw new OrderIdNullException("delivery id must be not null ");
        }
        logger.info("get from delivery microservice Delivery info");

        //   DeliveryResponse deliveryResponse= restTemplate.getForObject("http://localhost:99/getDelivery/{"+deliveryId+"}",DeliveryResponse.class);

        return DeliveryDto.builder()
                .idDeliver(5)
                .linkGPS("GPS")
                .phoneNumber("0664904084")
                .deliveryType(DeliveryType.home)
                .idAddress(1)
                .deliveryDuration(15)
                .email("email@mail.com")
                .phone("1254876")
                .deliveryNumber("1254TFX8")
                .deliveryStatus(DeliveryStatus.delivered)
                .startDateDelivery(new Date(System.currentTimeMillis()))
                .endDateDelivery(new Date(System.currentTimeMillis()))
                .deliveryDuration(2).idEnterprise(5).idOrder(5)
                .build();
    }

    public DeliveryDto getDeliveryByOrderId(Integer orderId) {
        if (orderId == null) {
            throw new OrderIdNullException("delivery id must be not null ");
        }
        logger.info("get from delivery microservice Delivery info by idorder");

        //   DeliveryResponse deliveryResponse= restTemplate.getForObject("http://localhost:99/getDelivery/{"+deliveryId+"}",DeliveryResponse.class);

        return DeliveryDto.builder()
                .idDeliver(5)
                .linkGPS("GPS")
                .phoneNumber("0664904084")
                .deliveryType(DeliveryType.home)
                .idAddress(1)
                .deliveryDuration(15)
                .email("email@mail.com")
                .phone("1254876")
                .deliveryNumber("1254TFX8")
                .deliveryStatus(DeliveryStatus.delivered)
                .startDateDelivery(new Date(System.currentTimeMillis()))
                .endDateDelivery(new Date(System.currentTimeMillis()))
                .deliveryDuration(2).idEnterprise(5).idOrder(5)
                .build();
    }


    public BuyerDto getBuyerOverview(Integer buyerId) {
        if (buyerId == null) {
            throw new BuyerIdNullException("buyer id must be not null ");
        }
        logger.info("get from order microservice Buyer info");
        //   BuyerResponse buyerResponse= restTemplate.getForObject("http://localhost:99/getBuyer/{"+buyerId+"}",BuyerResponse.class);
        return BuyerDto.builder().
                idBuyer(5)
                .firstNameB("First name").
                lastNameB("last name").
                phoneBuyer("0664964094").
                taxIdentificationNumber("taxIdentificationNumber").
                emailBuyer("emailBuyer@mail.com").
                registerCommercialNumberB("registerCommercialNumberB").
                buyerStatus(BuyerStatus.person).
                companyName("company Name").
                idAddress(1)
                .build();
    }


    public SellerDto getSeller(Integer sellerId) {
        if (sellerId == null) {
            throw new SellerIdNullException("seller id must be not null ");
        }
        logger.info("get from order microservice Seller info");

        return  SellerDto.builder().idEnterprise(5).enterpriseName("name").idAddress(1).accountNumber("xx10").
                accountType("accountataype").idContact(1).deliveryTime(12).reservationTime(2).publicPrivate("private").transportCosts(120).registerCommercialNumberS("125ED5").
                taxIdentificationNumberS("tax74").webSite("webSite").
                paymentMethods( (List.of(PaymentMethod.cib,PaymentMethod.dhahabia))).
                build();
    }

    public JsonObject  getAddress(int idAddress)
    {
        //restemplate dilivery

        var address= AddressDto.builder().idAddress(1).idCommune(1).codeZip("512").city("kab");
        //restemplate delivery get commune
        CommuneDto commune=new CommuneDto(1,"tamza",2);

        //restemplate delivery get wilaya

        WilayaDto wilaya =new WilayaDto(1,"khenchela");

        //remplace idwilaya par wilayaObject et idcommune par communeObject
        Gson gson=new Gson();
        String communejson= gson.toJson(commune);
        JsonObject jsoncommune = new JsonParser().parse(communejson).getAsJsonObject();

        jsoncommune.add("idWilaya",gson.toJsonTree(wilaya));

        String addressjson= gson.toJson(address);
        JsonObject jsonaddress = new JsonParser().parse(addressjson).getAsJsonObject();

        jsonaddress.add("idCommune",gson.toJsonTree(jsoncommune));


       return  jsonaddress;


    }
    public ArrayList<ProductDto> getProduct(int idOrder)

    {
        //resttemplate get list product
        ArrayList<ProductDto> productlist=new ArrayList<>();
        ProductDto prd=new ProductDto(5, "11D0GF5V00555", "Regular medicines", 1, 95,
        "DZD","CA2","image/product1/CA2/pro5.jpg",
        215,
        "moyen product .. bla bla bla bla",1200,12,4);
        productlist.add(prd);
        return  productlist;


    }
    public  ContactDto getContactEnterprise(int identerprise)
    {
        //get contact from delivery
        ContactDto contact=new ContactDto(1,"sellerfirstname","sellerlastname",new Date(System.currentTimeMillis()),"email@gmail.com","0124585",1);
       return contact;
    }

}
