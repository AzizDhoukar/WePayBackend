package org.example.wepaybackend.payment.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    private String mobileNo;

    @JsonIgnore
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Wallet wallet;

    public User (@NotNull String mobileNo){
        this.mobileNo = mobileNo;
        this.wallet = new Wallet(1000.0, this);
    }
}
