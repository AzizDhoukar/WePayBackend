package org.example.wepaybackend.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.wepaybackend.payment.models.EmailAndAmount;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SplitPaymentRequest {
    private String paymentId;
    List<EmailAndAmount> list;
}
