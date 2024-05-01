package org.example.wepaybackend.payment.controllers;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.payment.Exeptions.UserNotFound;
import org.example.wepaybackend.payment.Exeptions.WalletNotFound;
import org.example.wepaybackend.payment.models.Transaction;
import org.example.wepaybackend.payment.models.User;
import org.example.wepaybackend.payment.models.Wallet;
import org.example.wepaybackend.payment.repositories.WalletRepository;
import org.example.wepaybackend.payment.requests.AddWalletRequest;
import org.example.wepaybackend.payment.requests.BankToWalletTransferRequest;
import org.example.wepaybackend.payment.requests.TransactionRequest;
import org.example.wepaybackend.payment.services.UserService;
import org.example.wepaybackend.payment.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    private final WalletRepository walletRepository;
    @GetMapping()
    public ResponseEntity<List<Wallet>> getAll() {
        return ResponseEntity.ok(walletRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(walletRepository.findById(id).orElseThrow(WalletNotFound::new));
    }

    @PostMapping("/create")
    public ResponseEntity<Wallet> createUser(@RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletRepository.save(wallet));
    }
    @PutMapping("update")
    public ResponseEntity<Wallet> updateUser(@RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletRepository.save(wallet));
    }
    @PostMapping
    public ResponseEntity<Wallet> add(@RequestBody AddWalletRequest request) {
        Wallet newWallet = walletService.addWallet(request);
        return ResponseEntity.ok(newWallet);
    }

    @PutMapping("/transfer")
    public ResponseEntity<Transaction> WalletTOWalletTransfer(@RequestBody TransactionRequest transactionRequest) throws WalletNotFound {
        Transaction transaction = walletService.fundTransfer(transactionRequest.getSourceMobileNo(), transactionRequest.getTargetMobileNo(), transactionRequest.getAmount(),transactionRequest.getUniqueId());
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/addMoney")
    public ResponseEntity<Transaction> addAmountFromBankToWallet(@RequestBody BankToWalletTransferRequest request) throws Exception {
        Transaction transaction = walletService.addMoney(request.getUserId(), request.getAmount());
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

}
