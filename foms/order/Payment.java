package foms.order;

public interface Payment {

    public boolean processPayment(double amount);
    public String getName();
    public boolean getPaymentStatus();
    public void setPaymentStatus(boolean availability);
}
