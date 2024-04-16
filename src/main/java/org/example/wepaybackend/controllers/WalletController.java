package org.example.wepaybackend.controllers;

import org.example.wepaybackend.Exeptions.WalletNotFound;
import org.example.wepaybackend.models.Transaction;
import org.example.wepaybackend.models.User;
import org.example.wepaybackend.models.Wallet;
import org.example.wepaybackend.repositories.WalletRepository;
import org.example.wepaybackend.requests.BankToWalletTransferRequest;
import org.example.wepaybackend.requests.TransactionRequest;
import org.example.wepaybackend.services.UserService;
import org.example.wepaybackend.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    private final WalletRepository walletRepository;

    private final UserService userServiceImpl;

    public WalletController(WalletService walletService, WalletRepository walletRepository, UserService userServiceImpl) {
        this.walletService = walletService;
        this.walletRepository = walletRepository;
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Wallet> viewWallet(@PathVariable("id") Integer uniqueId) throws WalletNotFound{
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
