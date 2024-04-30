package org.example.wepaybackend.payment.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;

    @CreatedDate
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDate;

    private double amount;

    private String description;

    @ManyToOne
    private Wallet  fromWallet;

    @ManyToOne
    private Wallet  toWallet;


    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private TransactionStatus status;

    public Transaction( Wallet fromWallet, Wallet toWallet, double amount, String description, TransactionStatus status) {
        this.amount = amount;
        this.description = description;
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.status = status;
    }

    public Transaction(Wallet toWallet, Double amount, String description, TransactionStatus status) {
        this.amount = amount;
        this.description = description;
        this.toWallet = toWallet;
        this.status = status;
    }

    public enum TransactionStatus {
        SUCCEED,
        FAILED,
        PENDING
    }
}
