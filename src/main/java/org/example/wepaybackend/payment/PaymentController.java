package org.example.wepaybackend.payment;

import lombok.AllArgsConstructor;
import org.example.wepaybackend.payment.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    @PostMapping("/execute")
    public ResponseEntity<PaymentExecutionResponse> executePayment(@RequestBody PaymentExecutionRequest request) {
        return ResponseEntity.ok(paymentService.executePayment(request));
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @DeleteMapping("/payments")
    public ResponseEntity<String> deleteAllPayments() {
        return ResponseEntity.ok(paymentService.deleteAllPayments());
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<ListPaymentsResponse> getAllPaymentsByPayerId(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getAllPaymentsByPayerId(id));
    }

    @GetMapping("/payments/formatted/{id}")
    public ResponseEntity<List<PaymentHistoryResponse>> getAllPaymentsByPayerIdForHistory(@PathVariable  Integer id) {
        return ResponseEntity.ok(paymentService.getAllPaymentsByPayerIdForHistory(id));
    }

    @PostMapping("/payments/split")
    public ResponseEntity<List<String>> splitPayment(@RequestBody SplitPaymentRequest request) {
        return ResponseEntity.ok(paymentService.splitPayment(request));
    }

    @GetMapping("/informations/{paymentId}")
    public ResponseEntity<PaymentInformationsResponse> getPaymentInformations(@PathVariable("paymentId") String paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentInformations(paymentId));
    }
}
