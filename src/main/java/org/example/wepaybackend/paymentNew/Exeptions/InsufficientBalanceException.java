package org.example.wepaybackend.paymentNew.Exeptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException (String message){
        super (message);
    }
    public InsufficientBalanceException (){}

}
