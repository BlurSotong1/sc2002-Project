package foms.management;

import foms.order.Payment;
import foms.workers.AdminWorker;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationsOnPaymentStatus {
    /**
     * Changes on payment status is performed by admin worker.
     */
    private AdminWorker admin;
    private static ArrayList<Payment> paymentList;

    /**
     * Constructor for OperationsOnPaymentStatus class
     * @param admin is the admin worker performing the change of payment status.
     */
    public OperationsOnPaymentStatus(AdminWorker admin){
        this.admin = admin;
    }

    /**
     * method for admin to control the payment status
     * if true, then will display in customers' payment list and can be used
     * if false, will not be display in customers' payment list
     */
    public static void updatePaymentStatus(){
        Scanner scanner = new Scanner(System.in);
        int adminChoice;
        while(true){
            try{
                System.out.println("Select a payment mode to update its status:\n");
                OperationsOnPaymentList.displayPaymentList();
                adminChoice=scanner.nextInt();
                Payment payment=OperationsOnPaymentList.findPayment(adminChoice-1);
                if(payment.getPaymentStatus()){
                    payment.setPaymentStatus(false);
                } else{
                    payment.setPaymentStatus(true);
                }

            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please insert a valid integer.");
            }catch(Exception e){
                System.out.println("Invalid payment mode selection. Please try again.");
            }

        }
    }




}
