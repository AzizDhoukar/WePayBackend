package org.example.wepaybackend.paymentNew.requests;

import lombok.Data;

@Data
public class BankToWalletTransferRequest {
    private Integer userId;
    private Double amount;
}
