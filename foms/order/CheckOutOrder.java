p
        ackage foms.order;

import foms.management.branch.Branch;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This is for customer to check out their order
 * We will handle payment processing and payment failure in this class
 */
public class CheckOutOrder implements Serializable {
    /**
     * The Customer who is ready to check out their order
     */
    private Customer customer;
    /**
     * The amount that the customer paid
     */
    private double amountPaid=0.0;

    /**
     * Creates the order to be checked out
     * @param customer This Customer want to check out
     */
    public CheckOutOrder(Customer customer){
        this.customer=customer;
    }




    /**
     * receipt printing with orderID
     * will be printed once customer checkout their order in the update order status function
     */
    public void printReceipt(){
        System.out.println("OrderID: "+customer.getCart().getOrderID());
        customer.getCart().displayCart();
        System.out.println("Total amount = "+customer.getCart().getAmount());
        System.out.println("Amount paid = "+amountPaid);
        System.out.println("Change = "+(amountPaid-customer.getCart().getAmount()));

    }

}