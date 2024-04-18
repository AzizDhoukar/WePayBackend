package org.example.wepaybackend.paymentNew.repositories;

import org.example.wepaybackend.paymentNew.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryNew extends JpaRepository<User, Integer> {

    public Optional<User> findByMobileNo (String mobileNo);
}
