package foms.order;

public class BankTransfer implements Payment{
    public boolean processPayment(double amount){
        return true;
    }
}
