package foms.order.payment;

import foms.order.payment.Payment;

import java.io.Serializable;

public class PayWave implements Serializable,Payment {

    final private String name = "Paywave";
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
        this.paymentStatus = status;
    }
}
