package com.dz.itinfinity.backend_payment.services;

import com.dz.itinfinity.backend_payment.Requests.TransactionRequest;
import com.dz.itinfinity.backend_payment.Response.TransactionConfirmResponse;
import com.dz.itinfinity.backend_payment.Response.TransactionResponse;
import com.dz.itinfinity.backend_payment.apis.PaymentApi;
import com.dz.itinfinity.backend_payment.domain.DTOs.*;
import com.dz.itinfinity.backend_payment.domain.entities.Transaction;

import com.dz.itinfinity.backend_payment.domain.enums.OrderStatus;
import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import com.dz.itinfinity.backend_payment.domain.enums.TransactionStatus;
import com.dz.itinfinity.backend_payment.exceptions.*;
import com.dz.itinfinity.backend_payment.repositories.TransactionRepository;

import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

import java.util.HashMap;

import java.util.Map;


@Service
@Transactional
public class PaymentService {
    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    PaymentApi paymentApi;



    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    RestTemplate restTemplate;


    @Autowired
    PaymentOverviewService paymentOverviewService;
    @Autowired
    GoogleDriveService googleDriveService ;

    @Bean
    public RestTemplate RestTemplate() {
        return new RestTemplate();
    }


    @Autowired
     KafkaTemplate<String, Map> kafkaTemplateOrder;



    @Autowired
     KafkaTemplate<String, String> kafkaTemplateSend;

    private static final String topicOrder="";

    private static final String topicSendMail="";

    public TransactionResponse createPayment(PaymentDto paymentDto) {
        if (paymentDto.getOrderID()==null||paymentDto.getBayerID()==null||paymentDto.getSellerID()==null||paymentDto.getDeliveryID()==null) {
            throw new OrderIdNullException("IDs  must be not null ");
        }
        if (paymentDto.getPaymentMethod() == PaymentMethod.cib) {
            return createPaymentCib(paymentDto);
        }
        if (paymentDto.getPaymentMethod() == PaymentMethod.dhahabia) {
            return createPaymentDhahabia(paymentDto);
        }
        throw new PaymentMethodNotSupportedException(paymentDto.getPaymentMethod() + " Method Not Supported Exception");
    }


     TransactionResponse createPaymentCib(PaymentDto paymentDto)  {


        // request transaction CIB to satim
        var transactionRequest = TransactionRequest.builder()
                .amountTransaction(paymentOverviewService.getOrderOverview(paymentDto.getOrderID()).getOrderTotal())
                .order_idOrder(paymentDto.getOrderID())
                .enterpriseAccountNumber(paymentOverviewService.getSeller(paymentDto.getSellerID()).getAccountNumber())
                .build();

        //TODO:Call Api Satim for create payment and get transactionNumber and redirect url
        logger.info("Call Api Satim for create payment and get transactionNumber and redirect url");

        Transaction transaction = Transaction.builder()
                .paymentMethod(PaymentMethod.cib)
                .transactionDate( new java.sql.Date(System.currentTimeMillis()))
                .order_idOrder(Math.toIntExact(paymentDto.getOrderID()))
                .amountTransaction(paymentOverviewService.getOrderOverview(paymentDto.getOrderID()).getOrderTotal())
                .enterpriseAccountNumber(paymentOverviewService.getSeller(paymentDto.getSellerID()).getAccountNumber())
                .
                        build();

            TransactionResponse resultCreate = Create(transactionRequest,transaction);

        return resultCreate;

    }

   TransactionResponse createPaymentDhahabia(PaymentDto paymentDto) {
 // request transaction EDhahabia to satim
        var transactionRequest = TransactionRequest.builder()
                .amountTransaction(paymentOverviewService.getOrderOverview(paymentDto.getOrderID()).getOrderTotal())
                .order_idOrder(paymentDto.getOrderID())
                .enterpriseAccountNumber(paymentOverviewService.getSeller(paymentDto.getSellerID()).getAccountNumber())
                .build();


        //TODO:Call Api Satim for create payment and get transactionNumber and redirect url
        logger.info("Call Api Satim for create payment and get transactionNumber and redirect url");

        Transaction transaction = Transaction.builder()
                .paymentMethod(PaymentMethod.dhahabia)
                .transactionDate(new java.sql.Date(System.currentTimeMillis()))
                .order_idOrder(Math.toIntExact(paymentDto.getOrderID()))
                .amountTransaction(paymentOverviewService.getOrderOverview(paymentDto.getOrderID()).getOrderTotal())
                .enterpriseAccountNumber(paymentOverviewService.getSeller(paymentDto.getSellerID()).getAccountNumber())
                .failureDescription("").
                        build();

        TransactionResponse resultCreate = Create(transactionRequest,transaction);


        return resultCreate;
    }

    public TransactionResponse Create(TransactionRequest  transactionRequest,Transaction transaction) {

        try {
              TransactionResponse resultCreate = paymentApi.create(transactionRequest);

          //  TransactionResponse resultCreate = new TransactionResponse("12GFDVR45", "", "failed");
           // TransactionResponse resultCreate = new TransactionResponse("HB555KH55RA", "satim.dz", "success");

            if (resultCreate.getStatus().equals("success")) {

                transaction.setTransactionStatus(TransactionStatus.waiting);
                transaction.setTransactionNumber(resultCreate.getTransactionNumber());
                System.out.println(transactionRepository.save(transaction));


                return resultCreate;
            }

            transaction.setTransactionStatus(TransactionStatus.failed);
            transaction.setFailureDescription("failed create api payment");
            System.out.println(transactionRepository.save(transaction));


            // status order cancel
            Map OrderChangeRequest =new HashMap();
            OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
            OrderChangeRequest.put("status", OrderStatus.cancel);
            sendStatusOrderKafka(OrderChangeRequest);

              throw new ExceptionFailedCreateAPI("failed create api payment");

        } catch (SatimException ex) {
            transaction.setTransactionStatus(TransactionStatus.failed);
            transactionRepository.save(transaction);

            // status order cancel
            Map OrderChangeRequest =new HashMap();
            OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
            OrderChangeRequest.put("status", OrderStatus.cancel);
            sendStatusOrderKafka(OrderChangeRequest);
            throw new SatimException("exception satim");  // exception of SATIM method create
        }
    }









    public boolean confirmPaymentCib(String transactionNumber,String payerId) {

        if (transactionNumber == null) {
            throw new TransactionNumberNullException("Transaction Number must be not null");
        }
        if (payerId == null) {
            throw new BuyerIdNullException("Bayer Id must be not null");
        }

        var transaction = transactionRepository.findFirstByTransactionNumber(transactionNumber).orElseThrow(
                new TransactionNotFoundException("Not found transaction by  number transaction =  " + transactionNumber)
        );

        //TODO:Call Api Satim for confirm  payment
        logger.info("Call Api Satim for confirm  payment");

       Boolean confirm= ConfirmCIB(transactionNumber,payerId,transaction);


        return confirm;
    }

    public boolean confirmPaymentDhahabia(String transactionNumber,String payerId) throws GeneralSecurityException, IOException, ParseException {

        if (transactionNumber == null) {
            throw new TransactionNumberNullException("Transaction Number must be not null");
        }
        if (payerId == null) {
            throw new BuyerIdNullException("Bayer Id must be not null");
        }

        var transaction = transactionRepository.findFirstByTransactionNumber(transactionNumber).orElseThrow(
                new TransactionNotFoundException("Not found transaction by number transaction = " + transactionNumber)
        );

        //TODO:Call Api Dhahabia for confirm  payment
        logger.info("Call Api Dhahabia for confirm  payment");

        Boolean confirm= ConfirmEDhahabia(transactionNumber,payerId,transaction);


        return confirm;
    }

     boolean ConfirmCIB(String transactionNumber,String payerId,Transaction transaction){
        String IdFichier;

        try {
            TransactionConfirmResponse resultConfirm = paymentApi.confirmPayment(transactionNumber+" "+payerId);
         //   TransactionConfirmResponse resultConfirm = new TransactionConfirmResponse(transactionNumber, "satim.dz", "success","payername");
            if(resultConfirm.getStatus().equals("success")) {

                transaction.setTransactionStatus(TransactionStatus.success);
                transaction.setPayerName(resultConfirm.getPayer());
                transactionRepository.save(transaction);

                //status order paid
                Map OrderChangeRequest =new HashMap();
                OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
                OrderChangeRequest.put("status", OrderStatus.paid);

                sendStatusOrderKafka(OrderChangeRequest);

                //sendemail
                OrderDto order= paymentOverviewService.getOrderOverview(transaction.getOrder_idOrder());
                DeliveryDto delivery=paymentOverviewService.getDeliveryByOrderId(transaction.getOrder_idOrder());
                try {
                    IdFichier=generateFile(transaction,order,order.getIdBuyer(),order.getIdEnterprise(),delivery);
                    sendMailKafka(IdFichier);
                }
                catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }




                return true;
            }
            transaction.setTransactionStatus(TransactionStatus.failed);
            transaction.setPayerName(resultConfirm.getPayer());
            transactionRepository.save(transaction);

            // status order cancel
            Map OrderChangeRequest =new HashMap();
            OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
            OrderChangeRequest.put("status", OrderStatus.cancel);
            sendStatusOrderKafka(OrderChangeRequest);

            return false;

        }catch (SatimException ex){
            transaction.setTransactionStatus(TransactionStatus.cancel);
            transaction.setPayerName(payerId);
            transactionRepository.save(transaction);

            // status order cancel
            Map OrderChangeRequest =new HashMap();
            OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
            OrderChangeRequest.put("status", OrderStatus.cancel);
            sendStatusOrderKafka(OrderChangeRequest);

            return false;

        }

    }
    Boolean ConfirmEDhahabia(String transactionNumber,String payerId,Transaction transaction)  {
        String IdFichier;
        try {
           TransactionConfirmResponse resultConfirm = paymentApi.confirmPayment(transactionNumber+" "+payerId);
          //  TransactionConfirmResponse resultConfirm = new TransactionConfirmResponse(transactionNumber, "", "failed","payername");
            if(resultConfirm.getStatus().equals("success")) {
                transaction.setTransactionStatus(TransactionStatus.success);
                transaction.setPayerName(resultConfirm.getPayer());
                transactionRepository.save(transaction);
                //status order paid
                Map OrderChangeRequest =new HashMap();
                OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
                OrderChangeRequest.put("status", OrderStatus.paid);

                sendStatusOrderKafka(OrderChangeRequest);
                //sendemail
               OrderDto order= paymentOverviewService.getOrderOverview(transaction.getOrder_idOrder());
               DeliveryDto delivery=paymentOverviewService.getDeliveryByOrderId(transaction.getOrder_idOrder());
                try {
                    IdFichier=generateFile(transaction,order,order.getIdBuyer(),order.getIdEnterprise(),delivery);
                    sendMailKafka(IdFichier);
                }
                catch (GeneralSecurityException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }



                return true;
            }

            transaction.setTransactionStatus(TransactionStatus.failed);
            transaction.setPayerName(resultConfirm.getPayer());
            transaction.setFailureDescription("description of failed");
            transactionRepository.save(transaction);

            //status order cancel
            Map OrderChangeRequest =new HashMap();
            OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
            OrderChangeRequest.put("status", OrderStatus.cancel);
            sendStatusOrderKafka(OrderChangeRequest);



            return false;

        }catch (SatimException ex){
            transaction.setTransactionStatus(TransactionStatus.cancel);
            transaction.setPayerName(payerId);
            transaction.setFailureDescription(ex.getMessage());
            transactionRepository.save(transaction);

            //status order cancel

            Map OrderChangeRequest =new HashMap();
            OrderChangeRequest.put("idOrder",transaction.getOrder_idOrder());
            OrderChangeRequest.put("status", OrderStatus.cancel);
            sendStatusOrderKafka(OrderChangeRequest);



            return false;

        }
    }

    String generateFile(Transaction transaction,OrderDto order,int idbuyer, int idseller,DeliveryDto Delivery) throws GeneralSecurityException, IOException, ParseException {


            BuyerDto b= paymentOverviewService.getBuyerOverview(idbuyer);
            SellerDto s=paymentOverviewService.getSeller(idseller);

            FacteurDto f= new FacteurDto("12TGFR85LK",new Date(),new Date());

          String file_properties= googleDriveService.CrateFile(b,s,order,Delivery,transaction,f);

            logger.info("send mail by send_mail_miceroservice");

            return file_properties;


    }

    public  boolean sendStatusOrderKafka(Map OrderChangeRequest){
        try {
            kafkaTemplateOrder.send(topicOrder,OrderChangeRequest);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public  boolean sendMailKafka(String IdFichier){
        try {
            kafkaTemplateSend.send(topicOrder,IdFichier);
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
