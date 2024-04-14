package org.example.wepaybackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class BankAccount {
    @Id
    private Integer accountNumber;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String ifscCode;

    @NotNull
    private String bankName;

    private double bankBalance;

    @OneToOne
    private Wallet walletId;


}
