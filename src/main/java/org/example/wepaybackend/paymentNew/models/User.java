package org.example.wepaybackend.paymentNew.models;


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
    private String userName;


    @NotNull
    private String mobileNo;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @JsonIgnore
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Wallet wallet;
}
