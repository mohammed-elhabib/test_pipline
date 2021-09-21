package com.dz.itinfinity.backend_payment.domain.entities;

import com.dz.itinfinity.backend_payment.composite_id.Transaction_id;
import com.dz.itinfinity.backend_payment.domain.enums.PaymentMethod;
import com.dz.itinfinity.backend_payment.domain.enums.TransactionStatus;
import com.dz.itinfinity.backend_payment.exceptions.TransactionNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Transaction_id.class)
public class Transaction {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransaction")
    int idTransaction;
    @Column(name = "transactionNumber")
    String transactionNumber;
    @Column(name = "transactionDate")
    @Id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date transactionDate;
    @Id
    @Column(name = "Order_idOrder")
    int order_idOrder;
    @Column(name = "transactionStatus")
    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;
    @Column(name = "paymentMethod")
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    @Column(name = "failureDescription")
    String failureDescription;
    @Column(name = "entrepriseAccountNumber")
    String enterpriseAccountNumber ;
    @Column(name = "payerName")
    String payerName ;
    @Column(name = "amountTransaction ")
    double amountTransaction ;



}
