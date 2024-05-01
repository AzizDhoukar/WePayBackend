package org.example.wepaybackend.payment.services;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.payment.models.AuthUser;
import org.example.wepaybackend.payment.models.Transaction;
import org.example.wepaybackend.payment.models.User;
import org.example.wepaybackend.payment.repositories.UserRepository;
import org.example.wepaybackend.payment.requests.PaymentRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll();
        return users;
    }

    public AuthUser getinfo(Integer id){
       //TODO make this api call work
        // AuthUser authUser = restTemplate.getForObject("http://localhost:8081/address-service/address/{id}", AuthUser.class, id);
        return new AuthUser();
    }

    public User create(String mobileNo) {
        return userRepository.save(new User(mobileNo));
    }
}
