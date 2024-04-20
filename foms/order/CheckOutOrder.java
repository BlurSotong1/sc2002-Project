package foms.order;

import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.CANCELLED;
import static foms.order.OrderStatus.PREPARING;

public class CheckOutOrder {
    Order orderToCheckOut;

    public CheckOutOrder(Order orderToCheckOut){
        this.orderToCheckOut=orderToCheckOut;
    }
    public void updateOrderStatus(Order orderToCheckOut) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        if (paymentHandling(orderToCheckOut)) {
            System.out.println("Thank you for choosing us.\n" +
                    "This is your receipt.");
            orderToCheckOut.setOrderStatus(PREPARING);
        } else {
            handlePaymentFailure(orderToCheckOut);
        }
    }

    public boolean paymentHandling(Order orderToCheckOut) {
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;
        boolean paymentStatus = false;

        while (true) {
            System.out.println("Select your payment mode:\n" +
                    "(1) Cash\n" +
                    "(2) PayWave\n" +
                    "(3) NETS\n" +
                    "(4) Bank Transfer\n" +
                    "(5) Scan QR code");

            try {
                userChoice = sc.nextInt();
                if (userChoice < 1 || userChoice > 5) {
                    System.out.println("Invalid item number. Please enter a valid item number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Enter a valid integer.");
                sc.next(); // Clear invalid input
            }

            switch (userChoice) {
                case 1:
                    Payment cash = new Cash();
                    paymentStatus=cash.processPayment(orderToCheckOut.getAmount());
                    break;
                case 2:
                    Payment paywave = new PayWave();
                    paymentStatus=paywave.processPayment(orderToCheckOut.getAmount());
                    break;
                case 3:
                    Payment nets = new NETS();
                    paymentStatus=nets.processPayment(orderToCheckOut.getAmount());
                    break;
                case 4:
                    Payment bt = new BankTransfer();
                    paymentStatus=bt.processPayment(orderToCheckOut.getAmount());
                    break;
                case 5:
                    Payment qr = new ScanQR();
                    paymentStatus=qr.processPayment(orderToCheckOut.getAmount());
                    break;
                default:
                    System.out.println("Returning to main menu.");
                    break;

            }

        }
        return paymentStatus;
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
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input mismatch! Enter a valid integer.");
            }
        }catch(Exception e){
            System.out.println("Something went wrong.");
            return;
        }

        private void displayPaymentModes(){

        }



    }

}