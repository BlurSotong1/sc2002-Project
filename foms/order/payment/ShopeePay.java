package foms.order.payment;

import java.io.Serializable;

public class ShopeePay implements Serializable,Payment {
    final private String name = "ShopeePay";
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
