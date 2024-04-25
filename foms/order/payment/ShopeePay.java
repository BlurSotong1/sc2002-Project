package foms.order.payment;

public class ShopeePay implements Payment {
        private String name = "ShopeePay";
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
