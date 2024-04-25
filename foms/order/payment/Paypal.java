package foms.order.payment;

import java.io.Serializable;

public class Paypal implements Serializable,Payment {
    final private String name = "Paypal";
    private boolean paymentStatus = true;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getPaymentStatus() {
        return paymentStatus;
    }

    @Override
    public void setPaymentStatus(boolean status) {
        paymentStatus = status;
    }
}
