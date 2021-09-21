package com.dz.itinfinity.backend_payment.repositories;

import com.dz.itinfinity.backend_payment.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {


    Optional<Transaction> findFirstByTransactionNumber(String transactionNumber);


}
