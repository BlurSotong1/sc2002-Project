package foms.management;

import foms.order.Payment;
import foms.workers.AdminWorker;

import java.util.ArrayList;
import java.util.Scanner;

public class OperationsOnPaymentList {
    private static AdminWorker admin;
    private static ArrayList<Payment> paymentList;
    public OperationsOnPaymentList(AdminWorker admin){
        this.admin=admin;
    }

    /**
     * we need method hasPermission to add payment method
     * add new payment to the payment list
     * @param payment new payment method to be added should be done in main function
     */
    public void addToPaymentList(Payment payment){
        if (admin != null && admin.hasPermission("add_payment")) {
            paymentList.add(payment);
            System.out.println("Payment method added successfully: " + payment.getName());
            OperationsOnPaymentStatus.updatePaymentStatus();
        } else {
            System.out.println("Insufficient permissions to add payment method.");
        }


    }

    /**
     * display all payment methods for admin
     */
    public static void displayPaymentList(){
        System.out.println("Payment Methods:");
        for (int i = 0; i < paymentList.size(); i++) {
            Payment paymentMethod = paymentList.get(i);
            System.out.println((i + 1) + ". " + paymentMethod.getName() + " : " + paymentMethod.getPaymentStatus());
        }
    }
}
