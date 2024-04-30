package org.example.wepaybackend.payment.repositories;

import org.example.wepaybackend.payment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByMobileNo (String mobileNo);
}
