package foms.order.payment;

import java.io.Serializable;

public class NETS implements Serializable,Payment {

    final private String name = "NETS";
    private boolean paymentStatus = true;
    @Override
    public String getName() {
        return "NETS";
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
