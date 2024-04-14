package org.example.wepaybackend.repositories;

import org.example.wepaybackend.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackAccountRepository extends JpaRepository<BankAccount, Integer> {
}
