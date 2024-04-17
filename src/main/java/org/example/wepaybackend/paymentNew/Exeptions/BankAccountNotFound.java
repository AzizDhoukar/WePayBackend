package org.example.wepaybackend.paymentNew.Exeptions;

public class BankAccountNotFound extends Exception{
    public BankAccountNotFound (String message){
        super (message);
    }
    public BankAccountNotFound (){}
}
