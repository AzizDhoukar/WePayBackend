package org.example.wepaybackend.services;

import org.example.wepaybackend.Exeptions.BankAccountNotFound;
import org.example.wepaybackend.Exeptions.InsufficientBalanceException;
import org.example.wepaybackend.Exeptions.UserNotFound;
import org.example.wepaybackend.models.BankAccount;
import org.example.wepaybackend.models.Transaction;
import org.example.wepaybackend.models.User;
import org.example.wepaybackend.models.Wallet;
import org.example.wepaybackend.repositories.BankAccountRepository;
import org.example.wepaybackend.repositories.TransactionRepository;
import org.example.wepaybackend.repositories.UserRepository;
import org.example.wepaybackend.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WalletService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final WalletRepository walletRepository;

    public WalletService(UserService userService, UserRepository userRepository, TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository, WalletRepository walletRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.walletRepository = walletRepository;
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
        //TODO
        return 0.0;
    }

    public Transaction addMoney(Integer userId, Double amount) throws UserNotFound, InsufficientBalanceException, BankAccountNotFound {
        Optional<User> customer = userRepository.findById(userId);
        if (customer.isEmpty()){ throw new UserNotFound("No user with the id:" + userId); }

        User user = customer.get();
        Wallet wallet = user.getWallet();

        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(wallet.getWalletId());
        if (optionalBankAccount.isEmpty()) {throw new BankAccountNotFound("User dose not have a linked bank account");}
        BankAccount bankAccount = optionalBankAccount.get();

        if(bankAccount.getBankBalance()==0 || bankAccount.getBankBalance()<amount) {throw new InsufficientBalanceException("Insufficient balance in bank");}

        bankAccount.setBankBalance(bankAccount.getBankBalance() - amount);
        wallet.setBalance(wallet.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
        walletRepository.save(wallet);

        Transaction transaction = new Transaction(wallet, amount, LocalDateTime.now(), "Fund Transfer from bank to Wallet Successfully !");
        transactionRepository.save(transaction);
        return transaction;



    }
}
