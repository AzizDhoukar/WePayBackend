package org.example.wepaybackend.requests;

import lombok.Data;

@Data
public class TransactionRequest {
    private String sourceMobileNo ;
    private String targetMobileNo;
    private Double amount;
    private String uniqueId;
}
