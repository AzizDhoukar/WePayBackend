package org.example.wepaybackend.payment;

import org.example.wepaybackend.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findById(Integer id);
    List<Payment> findPaymentsByPayer(String Email);
    Payment findByPaymentId(String paymentId);

    List<Payment> findPaymentsByPayerId(Integer id);
    List<Payment> findPaymentsByBeneficiaryId(Integer id);
}
