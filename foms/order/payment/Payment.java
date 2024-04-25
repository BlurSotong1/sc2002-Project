package foms.order.payment;


import java.io.Serializable;

// Define the Payment interface
public interface Payment {
    String getName();
    boolean getPaymentStatus();
    void setPaymentStatus(boolean status);

}
