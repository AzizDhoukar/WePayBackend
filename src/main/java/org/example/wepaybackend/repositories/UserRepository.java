package org.example.wepaybackend.repositories;

import org.example.wepaybackend.models.User;
import org.example.wepaybackend.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByMobileNo (String mobileNo);
}
