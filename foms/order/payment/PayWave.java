package foms.order.payment;

import foms.order.payment.Payment;

public class PayWave implements Payment {

    private String name = "Paywave";
    private boolean paymentStatus;


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

