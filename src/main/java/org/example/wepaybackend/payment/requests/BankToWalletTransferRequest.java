package org.example.wepaybackend.payment.requests;

import lombok.Data;

@Data
public class BankToWalletTransferRequest {
    private Integer userId;
    private Double amount;
}
