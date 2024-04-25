package foms.order;

public class NETS implements Payment {
    private String name = "Nets";
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
