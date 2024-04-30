package org.example.wepaybackend.payment.controllers;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.payment.Exeptions.WalletNotFound;
import org.example.wepaybackend.payment.models.Transaction;
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

    @PostMapping
    public ResponseEntity<Wallet> add(@RequestBody AddWalletRequest request) {
        Wallet newWallet = walletService.addWallet(request);
        return ResponseEntity.ok(newWallet);
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAll(){
        List<Wallet> wallets = walletRepository.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> viewWallet(@PathVariable("id") Integer uniqueId) throws WalletNotFound {
        Optional<Wallet> wallet = walletRepository.findById(uniqueId);
        if (wallet.isEmpty()){
            throw new WalletNotFound("No wallet with this id" + uniqueId);
        }
        return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
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
