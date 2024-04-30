package org.example.wepaybackend.payment.services;

import lombok.RequiredArgsConstructor;
import org.example.wepaybackend.payment.Exeptions.TranscationNotFound;
import org.example.wepaybackend.payment.Exeptions.UserNotFound;
import org.example.wepaybackend.payment.models.Transaction;
import org.example.wepaybackend.payment.models.User;
import org.example.wepaybackend.payment.models.Wallet;
import org.example.wepaybackend.payment.repositories.TransactionRepository;
import org.example.wepaybackend.payment.repositories.UserRepository;
import org.example.wepaybackend.payment.repositories.WalletRepository;
import org.example.wepaybackend.payment.requests.PaymentRequest;
import org.example.wepaybackend.payment.requests.SplitPaymentRequest;
import org.springframework.stereotype.Service;


import static org.example.wepaybackend.payment.models.Transaction.TransactionStatus.PENDING;
import static org.example.wepaybackend.payment.models.Transaction.TransactionStatus.SUCCEED;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final EmailService emailService;

    public Transaction createPayment (PaymentRequest request){
        return createTransaction(request.getSellerId(), request.getUserId(), request.getAmount());
    }

    public Transaction createSplitPayment(SplitPaymentRequest request) {
        Transaction t = null;
        for (SplitPaymentRequest.SplitDetail split : request.getSplitDetails()) {
            t = createTransaction(request.getSellerId(), split.getUserId(), split.getAmount());
        }
        return t;
    }


    private Transaction createTransaction(Integer sellerId, Integer userId, double amount){
        User seller = userRepository.findById(sellerId).orElseThrow(UserNotFound::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        Wallet sellerWallet = seller.getWallet();
        Wallet userWallet = user.getWallet();

        Transaction transaction = new Transaction(userWallet, sellerWallet, amount,  "transaction between "+seller.getUserId() + user.getUserId(), PENDING);
        return transactionRepository.save(transaction);
    }


    public Transaction executePayment(Integer id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(TranscationNotFound::new);
        Wallet sellerWallet = transaction.getToWallet();
        Wallet userWallet = transaction.getFromWallet();
        double amount = transaction.getAmount();

        sellerWallet.setBalance(sellerWallet.getBalance() + amount);
        userWallet.setBalance(userWallet.getBalance() - amount);

        walletRepository.save(sellerWallet);
        walletRepository.save(userWallet);

        transaction.setStatus(SUCCEED);
        return transactionRepository.save(transaction);
    }
}
