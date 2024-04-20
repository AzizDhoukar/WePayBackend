package org.example.wepaybackend.authentificationNew.services;

import org.example.wepaybackend.authentificationNew.models.User;
import org.example.wepaybackend.authentificationNew.repositoies.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll();

        return users;
    }
}
