package org.example.wepaybackend.Exeptions;

public class BankAccountNotFound extends Exception{
    public BankAccountNotFound (String message){
        super (message);
    }
    public BankAccountNotFound (){}
}
