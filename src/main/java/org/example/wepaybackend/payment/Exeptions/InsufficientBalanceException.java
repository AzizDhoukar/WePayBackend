package org.example.wepaybackend.payment.Exeptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException (String message){
        super (message);
    }
    public InsufficientBalanceException (){}

}
