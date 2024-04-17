package org.example.wepaybackend.paymentNew.repositories;

import org.example.wepaybackend.paymentNew.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
