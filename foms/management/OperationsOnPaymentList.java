package foms.management;

import foms.order.Payment;
import foms.workers.AdminWorker;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationsOnPaymentList implements Serializable {
    private static AdminWorker admin;

    public OperationsOnPaymentList(AdminWorker admin) {
        this.admin = admin;
    }

    /**
     * add payment method to the payment list
     * will go through the Branch list then display Branch
     * find payment to add in the branch payment list
     * if it is not found in the branch list yet, can add , otherwise no
     */
    public void addPayment() {
        Scanner scanner = new Scanner(System.in);
        int branchChoice;
        String paymentMethod;
        Branch branch = null;
        while (true) {
            System.out.println("Select the branch that you want to add your payment method: ");
            BranchList.displayBranchNames();
            try {
                branchChoice = scanner.nextInt();
                branch = BranchList.findBranch(branchChoice - 1);

                System.out.println("These are the current payment methods:");
                branch.getPaymentList().displayPaymentList();

                System.out.println("Enter the new payment method (Enter 0 to exit):");
                paymentMethod = scanner.next();

                if (paymentMethod.equals("0")) {
                    System.out.println("Returning to previous page...");
                    return;
                }

                if (!isPaymentInPaymentList(branch, paymentMethod)){
                    branch.getPaymentList().addCreatedPayment(paymentMethod);
                    System.out.println("Payment method "+paymentMethod+" added successfully.");
                    branch.getPaymentList().displayPaymentList();
                }else {
                    System.out.println("Payment method already exists.");
                }

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Branch not found. Enter a valid branch index.");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "Error. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input.");
            }

        }
    }

    /**
     * remove payment method to the payment list
     * will go through the Branch list then display Branch
     * find payment to remove from the branch payment list
     * if it is found in the branch list yet, can remove , otherwise no
     *
     * @param payment new payment method to be removed should be done in main function
     */
    public void removeFromPaymentList(Payment payment) {
        Scanner scanner = new Scanner(System.in);
        int branchChoice;
        Branch branch;
        while (true) {
            System.out.println("Select the branch that you want to remove your payment method: ");
            BranchList.displayBranchNames();
            try {
                branchChoice = scanner.nextInt();
                branch =BranchList.findBranch(branchChoice-1);



            } catch (InputMismatchException e) {
                System.out.println("Branch not found. Enter a valid branch index.");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "Error. Please try again.");
            }
        }
    }

    public boolean isPaymentInPaymentList(Branch branch, String paymentMethod) {
        for (Payment paymentInPaymentList : branch.getPaymentList().getAvailablePayments()) {
            if (paymentMethod.equals(paymentInPaymentList.getName())) {
                return true;
            }
        }
        return false;
    }
}
