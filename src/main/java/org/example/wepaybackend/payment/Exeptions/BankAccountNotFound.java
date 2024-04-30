package org.example.wepaybackend.payment.Exeptions;

public class BankAccountNotFound extends Exception{
    public BankAccountNotFound (String message){
        super (message);
    }
    public BankAccountNotFound (){}
}
