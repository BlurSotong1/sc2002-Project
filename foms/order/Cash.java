package foms.order;

public class Cash implements Payment{
    public boolean processPayment(double amount){
        return true;
    }
}
