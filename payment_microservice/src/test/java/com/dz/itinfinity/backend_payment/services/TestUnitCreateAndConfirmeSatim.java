package com.dz.itinfinity.backend_payment.services;

import com.dz.itinfinity.backend_payment.Requests.TransactionRequest;
import com.dz.itinfinity.backend_payment.Response.TransactionConfirmResponse;
import com.dz.itinfinity.backend_payment.Response.TransactionResponse;
import com.dz.itinfinity.backend_payment.apis.PaymentApi;
import com.dz.itinfinity.backend_payment.domain.DTOs.*;
import com.dz.itinfinity.backend_payment.domain.entities.Transaction;
import com.dz.itinfinity.backend_payment.exceptions.ExceptionFailedCreateAPI;
import com.dz.itinfinity.backend_payment.exceptions.SatimException;
import com.dz.itinfinity.backend_payment.repositories.TransactionRepository;
import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;



public class TestUnitCreateAndConfirmeSatim {

    @InjectMocks
    PaymentService paymentService;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    PaymentService paymentSend;

    @Mock
    TransactionRequest transactionRequest;
    @Mock
    Transaction transaction;
    @Mock
    PaymentApi paymentApi;
    @Mock
    KafkaTemplate<String, Map> kafkaorder;
    @Mock
    KafkaTemplate<String, String> kafkasend;
    @Mock
    PaymentOverviewService paymentOverviewService;
    @Mock
    GoogleDriveService googleDriveService;
    @Mock
    BuyerDto buyerDto;
    @Mock
    SellerDto sellerDto;
    @Mock
    DeliveryDto deliveryDto;
    @Mock
    OrderDto orderDto;
    @Mock
    FacteurDto facteurDto;



    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);


    }

    @Test
    public  void  CreateTransactionSatimSuccess()  {
        TransactionResponse transactionResponse=new TransactionResponse("1234","satim.dz","success");
        when(paymentApi.create(transactionRequest)).thenReturn(transactionResponse);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        TransactionResponse response=paymentService.Create(transactionRequest,transaction);
        assertEquals(response.getStatus(),"success");

    }
    @Test
    public  void  CreateTransactionSatimFailed(){
        TransactionResponse transactionResponse=new TransactionResponse(null,"","faild");
        when(paymentApi.create(transactionRequest)).thenReturn(transactionResponse);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        assertThrows(ExceptionFailedCreateAPI.class, () -> paymentService.Create(transactionRequest,transaction));


    }
    @Test
    public  void  CreateTransactionSatimException(){

        when(paymentApi.create(transactionRequest)).thenThrow(SatimException.class);

        when(transactionRepository.save(transaction)).thenReturn(transaction);
        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        assertThrows(SatimException.class, () -> paymentService.Create(transactionRequest,transaction));


    }
    @Test
    public  void  ConfirmTransactionCIBSuccess() throws GeneralSecurityException, IOException, ParseException {

        TransactionConfirmResponse transactionResponse=new TransactionConfirmResponse("1234","","success","payername");
        String transactionNumber="XM125D";
        String payerId="xx";

      when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
      when(paymentSend.sendMailKafka(anyString())).thenReturn(true);

        when(paymentOverviewService.getOrderOverview(anyInt())).thenReturn(orderDto);
        when(paymentOverviewService.getSeller(anyInt())).thenReturn(sellerDto);
        when(paymentOverviewService.getBuyerOverview(anyInt())).thenReturn(buyerDto);
        when(paymentOverviewService.getDeliveryByOrderId(anyInt())).thenReturn(deliveryDto);
        when(googleDriveService.CrateFile(buyerDto,sellerDto,orderDto,deliveryDto,transaction,facteurDto)).thenReturn("");
        when(paymentSend.generateFile(transaction,orderDto,0,0,deliveryDto)).thenReturn(anyString());


        when(paymentApi.confirmPayment(transactionNumber+" "+payerId)).thenReturn(transactionResponse);
        when(transactionRepository.findFirstByTransactionNumber(anyString())).thenReturn(Optional.ofNullable(transaction));
       Boolean confirm=  paymentService.ConfirmCIB(transactionNumber,payerId,transaction);

        assertEquals(confirm,true);


    }

    @Test
    public  void  ConfirmTransactionCIBFailed(){

        TransactionConfirmResponse transactionResponse=new TransactionConfirmResponse("1234","","failed","payername");
        String transactionNumber="XM125D";
        String payerId="xx";
        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        when(paymentApi.confirmPayment(transactionNumber+" "+payerId)).thenReturn(transactionResponse);
        when(transactionRepository.findFirstByTransactionNumber(anyString())).thenReturn(Optional.ofNullable(transaction));
        Boolean confirm=  paymentService.ConfirmCIB(transactionNumber,payerId,transaction);
        assertEquals(confirm,false);


    }
    @Test
    public  void  ConfirmTransactionCIBExceptinSatim(){


        String transactionNumber="XM125D";
        String payerId="xx";
        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        when(paymentApi.confirmPayment(transactionNumber+" "+payerId)).thenThrow(SatimException.class);
        when(transactionRepository.findFirstByTransactionNumber(anyString())).thenReturn(Optional.ofNullable(transaction));
        Boolean confirm=  paymentService.ConfirmCIB(transactionNumber,payerId,transaction);
        assertEquals(confirm,false);

    }
    @Test
    public  void  ConfirmTransactionEdahabiaSuccess() throws GeneralSecurityException, IOException, ParseException {

        TransactionConfirmResponse transactionResponse=new TransactionConfirmResponse("1234","","success","payername");
        String transactionNumber="XM125D";
        String payerId="xx";

        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        when(paymentSend.sendMailKafka(anyString())).thenReturn(true);

        when(paymentOverviewService.getOrderOverview(anyInt())).thenReturn(orderDto);
        when(paymentOverviewService.getSeller(anyInt())).thenReturn(sellerDto);
        when(paymentOverviewService.getBuyerOverview(anyInt())).thenReturn(buyerDto);
        when(paymentOverviewService.getDeliveryByOrderId(anyInt())).thenReturn(deliveryDto);
        when(googleDriveService.CrateFile(buyerDto,sellerDto,orderDto,deliveryDto,transaction,facteurDto)).thenReturn("");
        when(paymentSend.generateFile(transaction,orderDto,0,0,deliveryDto)).thenReturn(anyString());


        when(paymentApi.confirmPayment(transactionNumber+" "+payerId)).thenReturn(transactionResponse);
        when(transactionRepository.findFirstByTransactionNumber(anyString())).thenReturn(Optional.ofNullable(transaction));
        Boolean confirm=  paymentService.ConfirmCIB(transactionNumber,payerId,transaction);

        assertEquals(confirm,true);



    }

    @Test
    public  void  ConfirmTransactionEdahabiaFailed(){

        TransactionConfirmResponse transactionResponse=new TransactionConfirmResponse("1234","","failed","payername");
        String transactionNumber="XM125D";
        String payerId="xx";
        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        when(paymentApi.confirmPayment(transactionNumber+" "+payerId)).thenReturn(transactionResponse);
        when(transactionRepository.findFirstByTransactionNumber(anyString())).thenReturn(Optional.ofNullable(transaction));
        Boolean confirm=  paymentService.ConfirmEDhahabia(transactionNumber,payerId,transaction);
        assertEquals(confirm,false);


    }
    @Test
    public  void  ConfirmTransactionEdahabiaExceptinSatim(){


        String transactionNumber="XM125D";
        String payerId="xx";
        when(paymentSend.sendStatusOrderKafka(any())).thenReturn(true);
        when(paymentApi.confirmPayment(transactionNumber+" "+payerId)).thenThrow(SatimException.class);
        when(transactionRepository.findFirstByTransactionNumber(anyString())).thenReturn(Optional.ofNullable(transaction));
        Boolean confirm=  paymentService.ConfirmEDhahabia(transactionNumber,payerId,transaction);
        assertEquals(confirm,false);

    }





}
