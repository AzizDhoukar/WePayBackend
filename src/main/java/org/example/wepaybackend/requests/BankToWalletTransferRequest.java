package org.example.wepaybackend.requests;

import lombok.Data;

@Data
public class BankToWalletTransferRequest {
    private Integer userId;
    private Double amount;
}
