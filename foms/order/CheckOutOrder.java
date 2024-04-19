package foms.order;

import java.util.InputMismatchException;
import java.util.Scanner;

import static foms.order.OrderStatus.PREPARING;

public class CheckOutOrder {
    public void updateOrderStatus(Order order){
        if(this.paymentHandling(order)){
            order.setOrderStatus(PREPARING);
        }
    }

    public boolean paymentHandling(Order order){
        Scanner sc= new Scanner(System.in);
        int userChoice;
        System.out.println("Select your payment mode:\n" +
                "(1) Cash\n" +
                "(2) PayWave\n" +
                "(3) NETS\n" +
                "(4) Bank Transfer\n" +
                "(5) Scan QR code");

        while (true) {
            try {
                userChoice = sc.nextInt();
                if (userChoice < 1 || userChoice > 5) {
                    System.out.println("Invalid item number. Please enter a valid item number.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("input mismatch! enter a valid integer.");
                sc.next(); // Clear invalid input
            }
        }
        switch(userChoice) {
            case 1:
                Payment cash = new Cash();
                if(cash.processPayment(order.getAmount())) return true;
                break;
            case 2:
                Payment paywave = new PayWave();
                if(paywave.processPayment(order.getAmount())) return true;
                break;
            case 3:
                Payment nets = new NETS();
                if(nets.processPayment(order.getAmount())) return true;
                break;
            case 4:
                Payment bankTransfer = new BankTransfer();
                if(bankTransfer.processPayment(order.getAmount())) return true;
                break;
            case 5:
                Payment qr = new ScanQR();
                if(qr.processPayment(order.getAmount())) return true;
                break;
            default:
                System.out.println("Returning back to main menu");
                break;
        }
        return false;

    }
}
