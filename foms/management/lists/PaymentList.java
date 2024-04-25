package foms.management.lists;

import foms.order.payment.Payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentList implements Serializable {
    private ArrayList<Payment> paymentList;

    public PaymentList() {
        this.paymentList = new ArrayList<>();
    }

    public void addCreatedPayment(Payment newPaymentMethod) {
        paymentList.add(newPaymentMethod);
    }

    public void removeIndexedPayment(int index) {
        if (index >= 0 && index < paymentList.size()) {
            Payment paymentToBoRemoved = paymentList.get(index);
            paymentToBoRemoved.setPaymentStatus(false);
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

    public void displayAllPayments(){
        if (paymentList.isEmpty()) {
            System.out.println("No available payment methods.");
        } else {
            System.out.println("Payment Methods:");
            for (Payment payment: paymentList) {
                int i=1;
                System.out.println(i + ". " + payment.getName()+" - "+ payment.getPaymentStatus());
            }
        }
    }

    public void displayAvailablePayments() {
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

    public Payment findPayment(int index) throws IndexOutOfBoundsException{
        if(index>=0 && index<paymentList.size()){
            return paymentList.get(index);
        }else{
            throw new IndexOutOfBoundsException("Payment not found!");
        }
    }
}
