package foms.order;

import foms.management.Branch;
import foms.management.OperationsOnPaymentList;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.CANCELLED;
import static foms.order.OrderStatus.PREPARING;

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
     * update order status
     * if customer paid, receipt will be printed and order status from PENDING to PREPARING
     * if customer failed to pay, will be brought to handle payment failure site
     */
    public void updateOrderStatus(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        if (paymentHandling()) {
            System.out.println("Thank you for choosing us.\n" +
                    "This is your receipt.");
            printReceipt();
            customer.getCart().setOrderStatus(PREPARING);
            customer.getBranch().getOrderList().addOrderToOrderList(customer.getCart());
        } else {
            handlePaymentFailure();
        }
    }

    /**
     * this is handle payment
     * @return true if customer fully paid
     *         false if customer fails to pay
     */
    public boolean paymentHandling() {
        Branch customerBranch = customer.getBranch();
        Scanner sc = new Scanner(System.in);
        int userChoice;
        double remainingAmount = customer.getCart().getAmount();

        while (remainingAmount!=0.00) {
            System.out.println("The total amount is $"+remainingAmount);
            System.out.println("Select your payment mode:\n" +
                    "Press 0 to go back to main menu.");
            customerBranch.getPaymentList().displayAvailablePayments();
            try {
                userChoice = sc.nextInt();
                if(userChoice==0){
                    System.out.println("Going back to main menu...");
                }
                Payment paymentModeChosen = customerBranch.getPaymentList().getPayment(userChoice-1);

                System.out.println("Please key in the amount that you want to pay.");
                amountPaid = sc.nextDouble();

                if (amountPaid > 0.00 && amountPaid <= remainingAmount) {
                    remainingAmount -= amountPaid;
                    System.out.println("Remaining amount to pay: $" + remainingAmount);

                    if (remainingAmount == 0.00) {
                        System.out.println("Payment completed successfully.");
                        return paymentModeChosen.processPayment(amountPaid);
                    }else {
                        System.out.println("Please pay the remaining amount.");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Enter a valid amount.");
                sc.next();
            }catch(Exception e){
                System.out.println(e.getMessage()+"Error occurred. ");
            }
        }
        return true;
    }

    /**
     * handle failed payment
     * ask customer whether to continue paying or cancel their order
     * if customer want to continue paying, then will be brought to payment handling site
     * if customer want to cancel order, then order status will be set to cancel and customer will be logged out
     */
    private void handlePaymentFailure(){
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        while(true){
            System.out.println("Sorry, we did not receive your payment.\n" +
                    "Please select your choice:\n" +
                    "(0) Returning back to main menu" +
                    "(1) Continue paying\n" +
                    "(2) Cancel Order\n");

            try {
                userChoice = scanner.nextInt();
                if(userChoice==0){
                    System.out.println("Going back to main menu...");
                    return;
                }
                else if (userChoice == 1){
                    paymentHandling();
                } else if (userChoice == 2) {
                    customer.getCart().setOrderStatus(CANCELLED);
                    //log out customer here?
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Enter a valid integer.");
            } catch(Exception e) {
                System.out.println("Something went wrong.");
            }
        }
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