package foms.order.payment;


import java.io.Serializable;

// Define the Payment interface
public interface Payment extends Serializable {
    String getName();
    boolean getPaymentStatus();
    void setPaymentStatus(boolean status);

}
