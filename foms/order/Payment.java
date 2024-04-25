package foms.order;


import java.io.Serializable;

// Define the Payment interface
public interface Payment {
    boolean processPayment(double amount);
    String getName();
    boolean getPaymentStatus();
    void setPaymentStatus(boolean status);
}
