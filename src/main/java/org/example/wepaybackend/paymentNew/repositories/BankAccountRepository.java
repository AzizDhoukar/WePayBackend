package org.example.wepaybackend.paymentNew.repositories;

import org.example.wepaybackend.paymentNew.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
