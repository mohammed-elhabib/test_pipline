package com.dz.itinfinity.backend_payment.controlles;

import com.dz.itinfinity.backend_payment.Response.TransactionResponse;
import com.dz.itinfinity.backend_payment.domain.DTOs.PaymentDto;
import com.dz.itinfinity.backend_payment.domain.entities.Transaction;
import com.dz.itinfinity.backend_payment.services.PaymentService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("api/payment")
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("create")
    public TransactionResponse createPayment(@RequestBody() PaymentDto paymentDto) throws Exception {
      return   paymentService.createPayment(paymentDto);

    }
    @GetMapping("confirmCib/{numbertransaction}/{payername}")
    public boolean confirmPaymentI(@PathVariable("numbertransaction") String transactionNumber, @PathVariable("payername")  String payerId){
        return   paymentService.confirmPaymentCib(transactionNumber,payerId);
    }

    @GetMapping("confirmDhabaia")
    public boolean confirmPayment(@RequestBody() String transactionNumber, String payerId)  {
        try {
            paymentService.confirmPaymentDhahabia(transactionNumber,payerId);
            return  true;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return  false;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        } catch (ParseException e) {
            e.printStackTrace();
            return  false;
        }

    }
}
