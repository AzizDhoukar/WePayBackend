package org.example.wepaybackend.services;

import org.example.wepaybackend.Exeptions.UserNotFound;
import org.example.wepaybackend.models.Transaction;
import org.example.wepaybackend.models.User;
import org.example.wepaybackend.models.Wallet;
import org.example.wepaybackend.repositories.TransactionRepository;
import org.example.wepaybackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WalletService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(UserService userService, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public Transaction fundTransfer(String sourceMobileNo, String targetMobileNo, Double amount, String uniqueId) throws UserNotFound{
        Optional<User> user = userRepository.findByMobileNo(sourceMobileNo);
        if (user.isEmpty()){ throw new UserNotFound("No user with the number:" + sourceMobileNo); }
        User customer = user.get();
        Wallet wallet = customer.getWallet();

        Optional<User> targetUser = userRepository.findByMobileNo(sourceMobileNo);
        if (targetUser.isEmpty()){ throw new UserNotFound("No user with the number:" + sourceMobileNo); }
        User targetCustomer = targetUser.get();


        Transaction transaction = new Transaction(wallet, amount, LocalDateTime.now(), "Fund Transfer from Wallet to Wallet Successfully !");
        transactionRepository.save(transaction);
        return transaction;
    }

    public Double showBalance(String uniqueId) {

    }
}
