package org.example.wepaybackend.payment.controllers;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.payment.Exeptions.TranscationNotFound;
import org.example.wepaybackend.payment.Exeptions.UserNotFound;
import org.example.wepaybackend.payment.models.Transaction;
import org.example.wepaybackend.payment.models.User;
import org.example.wepaybackend.payment.repositories.UserRepository;
import org.example.wepaybackend.payment.requests.PaymentRequest;
import org.example.wepaybackend.payment.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findById(id).orElseThrow(UserNotFound::new));
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody String mobileNo) {
        return ResponseEntity.ok(userService.create(mobileNo));
    }
    @PutMapping("update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
}
