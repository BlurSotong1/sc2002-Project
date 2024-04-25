package foms.management.lists;

import foms.order.payment.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentList implements Serializable {

    /**
     * list of payment methods of a branch
     */
    private ArrayList<Payment> paymentList;

    /**
     * Constructor for PaymentList
     */
    public PaymentList() {
        this.paymentList = new ArrayList<>();
        paymentList.add(new NETS());
        paymentList.add(new Paypal());
        paymentList.add(new PayWave());
        paymentList.add(new ShopeePay());
    }

    /**
     * add payment.
     * @param newPaymentMethod
     */
    public void addCreatedPayment(Payment newPaymentMethod) {
        paymentList.add(newPaymentMethod);
    }

    /**
     * remove payment.
     * @param index
     */
    public void removeIndexedPayment(int index) throws IndexOutOfBoundsException {
        paymentList.remove(index);
        if (index>=paymentList.size())
            throw new IndexOutOfBoundsException();
    }

    /**
     *
     * @param index
     * @return
     */
    public Payment getPayment(int index) {
        return paymentList.get(index); //TODO Exception
    }

    /**
     * @return list of payments
     */
    public List<Payment> getAvailablePayments() {
        return paymentList.stream().filter(Payment::getPaymentStatus).collect(Collectors.toList());
    }

    /**
     * Display all the payments that can be added to a branch.
     */
    public void displayAllPayments(){
        System.out.println("List of payments:");
        System.out.println("1. NETS\n2. Paypal\n3. PayWave\n4. ShopeePay");
    }

    /**
     * Display all the payments available in the branch.
     */
    public void displayAvailablePayments() {
        int i=1;
        for (Payment payment : paymentList) {
            System.out.println(i+". "+payment.getName());
            i++;
        }

    }

    /**
     * check if payment is in payment list
     * @return true if payment is in payment list, false if payment is not in the payment list
     */
    public boolean checkPayment(Payment payment) {
        return paymentList.contains(payment);
    }
}
