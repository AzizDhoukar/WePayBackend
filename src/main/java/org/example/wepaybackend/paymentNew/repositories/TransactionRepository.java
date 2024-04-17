package org.example.wepaybackend.paymentNew.repositories;

import org.example.wepaybackend.paymentNew.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
