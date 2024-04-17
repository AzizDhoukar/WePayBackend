package org.example.wepaybackend.payment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    String beneficiaryId;
    Double total;
    String currency;
    String method;
    String cancelUrl;
    String successUrl;
    PaymentStatus paymentStatus = PaymentStatus.PENDING;
}
