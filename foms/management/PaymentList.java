package foms.management;

import foms.order.Payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentList implements Serializable {
    private ArrayList<Payment> paymentList;

    public PaymentList() {
        this.paymentList = new ArrayList<>();
    }

    public void addCreatedPayment(String payment) {
        paymentList.add(payment);
    }

    public void removeIndexedPayment(int index) {
        if (index >= 0 && index < paymentList.size()) {
            paymentList.remove(index);
        }
    }

    public Payment getPayment(int index) {
        List<Payment> availablePayments = getAvailablePayments();
        if (index >= 0 && index < availablePayments.size()) {
            return availablePayments.get(index);
        }
        return null;
    }

    public List<Payment> getAvailablePayments() {
        return paymentList.stream().filter(Payment::getPaymentStatus).collect(Collectors.toList());
    }

    public void displayPaymentList() {
        List<Payment> availablePayments = getAvailablePayments();
        if (availablePayments.isEmpty()) {
            System.out.println("No available payment methods.");
        } else {
            System.out.println("Available Payment Methods:");
            for (int i = 0; i < availablePayments.size(); i++) {
                System.out.println((i + 1) + ". " + availablePayments.get(i).getName());
            }
        }
    }
}
