package com.dz.itinfinity.backend_payment;

import com.dz.itinfinity.backend_payment.apis.PaymentApi;
import com.dz.itinfinity.backend_payment.domain.DTOs.PaymentDto;
import com.dz.itinfinity.backend_payment.domain.entities.Transaction;
import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import com.dz.itinfinity.backend_payment.domain.enums.TransactionStatus;
import com.dz.itinfinity.backend_payment.repositories.TransactionRepository;
import com.dz.itinfinity.backend_payment.services.PaymentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@SpringBootApplication
public class BackendPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendPaymentApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


  //  @Bean
    CommandLineRunner init(TransactionRepository transactionRepository,PaymentService paymentService) {
        return args -> {
        /*    transactionRepository.findAll().forEach(System.out::println);
           PaymentDto paydhahabia=new PaymentDto(PaymentMethod.dhahabia,5,5,5,5);
            String numberTransaction=paymentService.createPayment(paydhahabia).getTransactionNumber();
            paymentService.confirmPaymentDhahabia(numberTransaction,"payername");
           /* PaymentDto paycib=new PaymentDto(PaymentMethod.cib,5,5,5,5);
           String numberTransaction= paymentService.createPayment(paycib).getTransactionNumber();
            paymentService.confirmPaymentCib(numberTransaction,"payername");*/
           // Transaction trn= new Transaction(1,"125UH5",new Date(System.currentTimeMillis()),5, TransactionStatus.success,PaymentMethod.dhahabia,"","","23DGF",120);
           // transactionRepository.save(trn);


        };
    }
}
