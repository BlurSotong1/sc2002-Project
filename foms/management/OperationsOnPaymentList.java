package foms.management;

import foms.order.Payment;
import foms.workers.AdminWorker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class OperationsOnPaymentList {
    private static Branch branch;
    public OperationsOnPaymentList(Branch branch){
        this.branch=branch;
    }

    /**
     * add new payment to the payment list
     * @param payment new payment method to be added should be done in main function
     */
    public void addToPaymentList(Payment payment){
        branch.getPaymentList().add(payment);
        System.out.println("Payment method added successfully: " + payment.getName());
        OperationsOnPaymentStatus.updatePaymentStatus();
    }

    /**
     * display all payment methods for admin
     */
    public static void displayPaymentList(){
        System.out.println("Payment Methods:");
        for (int i = 0; i < branch.getPaymentList().size(); i++) {
            Payment paymentMethod = branch.getPaymentList().get(i);
            System.out.println((i + 1) + ". " + paymentMethod.getName() + " : " + paymentMethod.getPaymentStatus());
        }
    }

    /**
     * find the payment method from the payment list
     * this method will be used when admin want to update the payment status in the payment list
     * @param index pass in the index of the payment method that we want to find
     * @return the payment method from the payment list
     */
    public static Payment findPayment(int index){
        if(index >=0 && index < branch.getPaymentList().size()){
            return branch.getPaymentList().get(index);
        }
        System.out.println("Payment method not found.");
        return null;
    }
}
