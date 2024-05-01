package org.example.wepaybackend.payment.controllers;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.payment.Exeptions.TranscationNotFound;
import org.example.wepaybackend.payment.models.Transaction;
import org.example.wepaybackend.payment.repositories.TransactionRepository;
import org.example.wepaybackend.payment.requests.PaymentRequest;
import org.example.wepaybackend.payment.requests.SplitPaymentRequest;
import org.example.wepaybackend.payment.services.EmailService;
import org.example.wepaybackend.payment.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final TransactionRepository transactionRepository;
    private final PaymentService paymentService;
    private final EmailService emailService;

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAll() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionRepository.findById(id).orElseThrow(TranscationNotFound::new));
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }
    @PostMapping("/createSplit")
    public ResponseEntity<Transaction> createSplitPayment(@RequestBody SplitPaymentRequest request) {
        return ResponseEntity.ok(paymentService.createSplitPayment(request));
    }
    @PostMapping("/executePayment/{id}")
    public ResponseEntity<Transaction> executePayment(@PathVariable Integer id){
        return ResponseEntity.ok(paymentService.executePayment(id));
    }
    @PostMapping("/emailTest")
    public void sendMail (){
        emailService.sendEmail("azizdhoukar2@gmail.com", "test email", "testestest");
        System.out.println("mail sent");
    }
}