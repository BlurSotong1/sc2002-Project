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

    public static boolean updatePaymentStatus(){
        Scanner scanner = new Scanner(System.in);
        int adminChoice;
        while(true){
            try{
                System.out.println("Select a payment mode to update its status:\n");
                OperationsOnPaymentList.displayPaymentList();
                adminChoice=scanner.nextInt();
                OperationsOnPaymentList.findPayment(adminChoice-1);
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please insert a valid integer.");
            }
        }
    }




}
