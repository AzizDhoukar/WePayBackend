package org.example.wepaybackend.paymentNew.services;

import org.example.wepaybackend.paymentNew.Exeptions.BankAccountNotFound;
import org.example.wepaybackend.paymentNew.Exeptions.InsufficientBalanceException;
import org.example.wepaybackend.paymentNew.Exeptions.UserNotFound;
import org.example.wepaybackend.paymentNew.models.BankAccount;
import org.example.wepaybackend.paymentNew.models.Transaction;
import org.example.wepaybackend.paymentNew.models.User;
import org.example.wepaybackend.paymentNew.models.Wallet;
import org.example.wepaybackend.paymentNew.repositories.BankAccountRepository;
import org.example.wepaybackend.paymentNew.repositories.TransactionRepository;
import org.example.wepaybackend.paymentNew.repositories.UserRepositoryNew;
import org.example.wepaybackend.paymentNew.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WalletService {
    private final UserService userService;
    private final UserRepositoryNew userRepositoryNew;
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;
    private final WalletRepository walletRepository;

    public WalletService(UserService userService, UserRepositoryNew userRepositoryNew, TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository, WalletRepository walletRepository) {
        this.userService = userService;
        this.userRepositoryNew = userRepositoryNew;
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.walletRepository = walletRepository;
    }

    public Transaction fundTransfer(String sourceMobileNo, String targetMobileNo, Double amount, String uniqueId) throws UserNotFound{
        Optional<User> user = userRepositoryNew.findByMobileNo(sourceMobileNo);
        if (user.isEmpty()){ throw new UserNotFound("No user with the number:" + sourceMobileNo); }
        User customer = user.get();
        Wallet wallet = customer.getWallet();

        Optional<User> targetUser = userRepositoryNew.findByMobileNo(sourceMobileNo);
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
        Optional<User> customer = userRepositoryNew.findById(userId);
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
