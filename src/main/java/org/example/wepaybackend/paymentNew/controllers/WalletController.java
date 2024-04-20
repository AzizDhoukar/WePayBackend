package org.example.wepaybackend.paymentNew.controllers;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.paymentNew.Exeptions.WalletNotFound;
import org.example.wepaybackend.paymentNew.models.Transaction;
import org.example.wepaybackend.paymentNew.models.Wallet;
import org.example.wepaybackend.paymentNew.repositories.WalletRepository;
import org.example.wepaybackend.paymentNew.requests.BankToWalletTransferRequest;
import org.example.wepaybackend.paymentNew.requests.TransactionRequest;
import org.example.wepaybackend.paymentNew.services.UserService;
import org.example.wepaybackend.paymentNew.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    private final WalletRepository walletRepository;

    private final UserService userServiceImpl;


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
