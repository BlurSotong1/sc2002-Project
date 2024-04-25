package foms.order;

import foms.management.Branch;
import foms.management.OperationsOnPaymentList;

import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.CANCELLED;
import static foms.order.OrderStatus.PREPARING;

/**
 * This is for customer to check out their order
 * We will handle payment processing and payment failure in this class
 */
public class CheckOutOrder {
    /**
     * The Customer who is ready to check out their order
     */
    private Customer customer;
    /**
     * The amount that the customer paid
     */
    private static double amountPaid=0.0;

    /**
     * Creates the order to be checked out
     * @param orderToCheckOut This Customer want to check out
     */
    public CheckOutOrder(Customer customer){
        this.customer=customer;
    }

    /**
     * update order status
     * if customer paid, receipt will be printed and order status from PENDING to PREPARING
     * if customer failed to pay, will be brought to handle payment failure site
     * @param orderToCheckOut customer's order
     */
    public void updateOrderStatus(Order orderToCheckOut) {
        Scanner scanner = new Scanner(System.in);
        if (paymentHandling(orderToCheckOut)) {
            System.out.println("Thank you for choosing us.\n" +
                    "This is your receipt.");
            printReceipt(orderToCheckOut);
            orderToCheckOut.setOrderStatus(PREPARING);
        } else {
            handlePaymentFailure(orderToCheckOut);
        }
    }

    /**
     * this is handle payment
     * @param customer This Customer who wants to check out
     * @return true if customer fully paid
     *         false if customer fails to pay
     */
    public boolean paymentHandling(Customer customer) {
        Branch customerBranch = customer.getBranch();
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;
        boolean paymentStatus = false;

        while (true) {
            System.out.println("Select your payment mode:\n");
            customerBranch.displayPaymentList();
            try {
                userChoice = sc.nextInt();


                if (OperationsOnPaymentList.findPayment(customerBranch,paymentChosen)) {
                    System.out.println("Invalid item number. Please enter a valid item number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Enter a valid integer.");
                sc.next();
            }
            }
        return false;
    }

    private void handlePaymentFailure(Order order){
        try(Scanner scanner = new Scanner(System.in)){
            int userChoice;
            System.out.println("Sorry, we did not receive your payment.\n" +
                    "Please select your choice:\n" +
                    "(1) Continue paying\n" +
                    "(2) Cancel Order");

            try {
                userChoice = scanner.nextInt();
                if (userChoice == 1){
                    paymentHandling(order);
                } else if (userChoice == 2) {
                    orderToCheckOut.setOrderStatus(CANCELLED);
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Enter a valid integer.");
            }
        }catch(Exception e){
            System.out.println("Something went wrong.");
        }
    }

    private void displayPaymentModes(){
        Branch customerBranch = customer.getBranch();
        OperationsOnPaymentList.displayPaymentList(customerBranch);
    }

    public void printReceipt(Order orderToCheckOut){
        System.out.println("OrderID: "+orderToCheckOut.getOrderID());
        orderToCheckOut.viewSingleOrderDetails(orderToCheckOut.getOrderID())
        System.out.println("Total amount = "+orderToCheckOut.getAmount());
        System.out.println("Amount paid = "+amountPaid);
        System.out.println("Change = "+(amountPaid-orderToCheckOut.getAmount()));

    }

}