package foms.order.payment;

import foms.order.Customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is for customer to check out their order
 * We will handle payment processing and payment failure in this class
 */
public class CheckOutProcess implements Serializable {
    /**
     * The Customer who is ready to check out their order
     */
    private Customer customer;

    /**
     * amount the customer paid for the bill
     */
    private double amountPaid = 0.0;

    private Payment paymentMethod;

    public CheckOutProcess(Customer customer) {
        this.customer = customer;
    }

    public void CheckOut () {
        System.out.println("Checking out Process");
        System.out.printf("Your total bill is: %.2f", customer.getOrder().getAmount());
        System.out.println("Proceed to checkout?\n" +
                "0. Continue Shopping" +
                "1. Checkout");

        Scanner scanner = new Scanner(System.in);

        String checkOutOrNot = "I love oop";

        while (!checkOutOrNot.equals("1")) { //if not 1 then continue prompting
            checkOutOrNot = scanner.next();

            if (checkOutOrNot.equals("0")) { //dont check out so return
                System.out.println("Returning to order screen!");
                return;
            } else { //wrong input
                System.out.println("Enter a valid input!");
            }
        }

        System.out.println("checking out now.");

        int maxIndex =1;
        for (Payment payment:customer.getBranch().getPaymentList().getAvailablePayments()) {
            System.out.printf("%d. %s",maxIndex,payment.getName());
            maxIndex++;
        }

        int choice;
        while (true) { //true loop to catch input mismatch error
            System.out.println("Please select a payment method (press 0 to exit): ");
            try {
                choice = scanner.nextInt();

                if (choice == 0) {
                    System.out.println("cancelled check out. returning..");
                    return;
                }

                if (choice > 0 && choice < maxIndex) { //valid indexing
                    this.paymentMethod = customer.getBranch().getPaymentList().getPayment(choice);
                    break;
                } else {
                    System.out.println("Enter a valid range!");
                }

            } catch (InputMismatchException e) {
                System.out.println("enter a number!");
            } catch (Exception e) {
                System.out.println("something went wrong");
            }
        }

        System.out.printf("Paying using %s", paymentMethod.getName());


    }


    /**
     * receipt printi6ng with orderID
     * will be printed once customer checkout their order in the update order status function
     */
    private void printReceipt() {
        System.out.println("OrderID: " + customer.getOrder().getOrderID());
        customer.getOrder().displayCart();
        System.out.println("Total amount = " + customer.getOrder().getAmount());
        System.out.println("Amount paid = " + amountPaid);
        System.out.println("Change = " + (amountPaid - customer.getOrder().getAmount()));

    }


}


