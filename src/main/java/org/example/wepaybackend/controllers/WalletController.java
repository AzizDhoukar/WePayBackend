package org.example.wepaybackend.controllers;

import org.example.wepaybackend.Exeptions.WalletNotFound;
import org.example.wepaybackend.models.Transaction;
import org.example.wepaybackend.models.User;
import org.example.wepaybackend.models.Wallet;
import org.example.wepaybackend.repositories.WalletRepository;
import org.example.wepaybackend.requests.TransactionRequest;
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

    private final CurrentUserSessionServiceImpl currentuserSesionServiceImpl;

    public WalletController(WalletService walletService, WalletRepository walletRepository, UserService userServiceImpl, CurrentUserSessionServiceImpl currentuserSesionServiceImpl) {
        this.walletService = walletService;
        this.walletRepository = walletRepository;
        this.userServiceImpl = userServiceImpl;
        this.currentuserSesionServiceImpl = currentuserSesionServiceImpl;
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

    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<Transaction> depositAmountFromWalletToBank(@PathVariable("id") String uniqueId, @PathVariable("amount") Double amount)
            throws UserNotException, InsufficientResourcesException, LoginException, InsufficientBalanceException {
        Transaction transaction = walletService.depositeAmount(uniqueId, amount);
        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }

    @PostMapping("/addMoney/{uniqueId}/{amount}")
    public ResponseEntity<User> addAmountFromBankToWallet(String uniqueId, Double amount) throws Exception {
        User user = walletService.addMoney(uniqueId, amount);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
