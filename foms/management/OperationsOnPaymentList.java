package foms.management;

import foms.order.Payment;
import foms.workers.AdminWorker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationsOnPaymentList {
    private static AdminWorker admin;

    public OperationsOnPaymentList(AdminWorker admin) {
        this.admin = admin;
    }

    /**
     * add new payment to the payment list
     *
     * @param payment new payment method to be added should be done in main function
     */
    public void addToPaymentList(Payment payment) {
        Scanner scanner = new Scanner(System.in);
        int branchChoice;
        while (true) {
            try {
                System.out.println("Select the branch that you want to add your payment method: ");
                OperationsOnBranchList.displayBranchNames();
                branchChoice = scanner.nextInt();
                Branch branch = OperationsOnBranchList.findBranch(branchChoice);

                if(findPayment(branch,payment)){
                    branch.getPaymentList().add(payment);
                    System.out.println("Payment method added successfully to " + branch.getName());
                }else{
                    System.out.println("Payment method is not found in the payment list.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
            }catch(Exception e){
                System.out.println("Error. Please try again.");
            }
        }
    }

    public void removeFromPaymentList(Payment payment) {
        Scanner scanner = new Scanner(System.in);
        int branchChoice;
        while (true) {
            try {
                System.out.println("Select the branch that you want to remove your payment method: ");
                OperationsOnBranchList.displayBranchNames();
                branchChoice = scanner.nextInt();
                Branch branch = OperationsOnBranchList.findBranch(branchChoice);
                if(findPayment(branch,payment)){
                    boolean remove = branch.getPaymentList().remove(payment);
                    if (remove) {
                        System.out.println("Payment method removed successfully to " + branch.getName());
                    } else {
                        System.out.println("Payment method is not in the payment list.");
                    }
                }else {
                    System.out.println("Payment method not found.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
            } catch(Exception e){
                System.out.println("Error. Please try again.");
            }
        }

    }

    /**
     * display all payment methods for admin
     */
    public static void displayPaymentList(Branch branch) {
        if (branch != null) {
            System.out.println("Payment Methods:");
            for (int i = 0; i < branch.getPaymentList().size(); i++) {
                Payment paymentMethod = branch.getPaymentList().get(i);
                System.out.println((i + 1) + ". " + paymentMethod.getName() + " : " + paymentMethod.getPaymentStatus());
            }
        }
    }

    /**
     * find the payment method from the payment list
     * this method will be used when admin want to update the payment status in the payment list
     *
     * @param branch pass in the index of the payment method that we want to find
     * @return the payment method from the payment list
     */
    public static boolean findPayment(Branch branch, Payment payment) {
        for (Payment availPayment : branch.getPaymentList()) {
            if (availPayment.getName().equals(payment.getName())) {
                return true;
            }
        }
        return false;
    }
}
