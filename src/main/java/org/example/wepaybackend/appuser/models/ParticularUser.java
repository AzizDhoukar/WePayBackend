package org.example.wepaybackend.appuser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.wepaybackend.appuser.models.data.AccountType;
import org.example.wepaybackend.appuser.models.data.AppUserRole;
import org.example.wepaybackend.payment.models.Payment;

import java.util.List;

@EntityListeners(ParticularUserListener.class)
@Getter
@Setter
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "tokens", "paymentList"})
public class ParticularUser extends AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String accountIdentifier;
    @OneToMany(mappedBy = "payer")
    private List<Payment> paymentList;
    public ParticularUser(String email,
                          String password,
                          AppUserRole appUserRole,
                          AccountType accountType,
                          Boolean locked,
                          Boolean enabled,
                          String firstName,
                          String lastName,
                          Double balance,
                          String phone) {
        super(email, password, appUserRole, accountType, locked, enabled, balance);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }


}
