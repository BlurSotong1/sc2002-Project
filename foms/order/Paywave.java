package foms.order;

import java.io.Serializable;

public class Paywave implements Payment, Serializable {

        private String name = "Paywave";
        private boolean paymentStatus;

        @Override
        public boolean processPayment(double amount) {
            return true;
        }

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


