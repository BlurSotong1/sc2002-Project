package foms.management.lists;

import foms.management.branch.Branch;
import foms.order.payment.Payment;
import foms.workers.AdminWorker;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Admin will use this class to add and remove payment
 */
public class OperationsOnPaymentList implements Serializable {
    /**
     * The admin who has access to this method
     */
    private AdminWorker admin;

    /**
     * Constructor with the given admin
     * @param admin is the admin worker performing the operations on branch list.
     */
    public OperationsOnPaymentList(AdminWorker admin) {
        this.admin = admin;
    }

    /**
     * add payment method to the payment list
     * will go through the Branch list then display Branch
     * display Branch's payment list
     * admin will be prompt to add payment method
     * find payment to add in the branch payment list
     * if it is not found in the branch list yet, can add, otherwise no
     */
    public void addPayment() {
        Scanner scanner = new Scanner(System.in);
        int branchChoice;
        int paymentMethod;
        Branch branch = null;
        Payment newPayment;
        while (true) {
            System.out.println("Select the branch that you want to add your payment method: ");
            BranchList.displayBranchNames();
            try {
                branchChoice = scanner.nextInt();
                branch = BranchList.findBranch(branchChoice - 1);

                System.out.println("Payment methods:");
                branch.getPaymentList().displayAllPayments();

                System.out.println("Enter the new payment method (Enter 0 to exit):");
                paymentMethod = scanner.nextInt();
                if(paymentMethod==0){
                    System.out.println("Returning to previous page.");
                    return;
                }

                newPayment = branch.getPaymentList().getPayment(paymentMethod-1);

                if(!newPayment.getPaymentStatus()){
                    branch.getPaymentList().addCreatedPayment(newPayment);
                    newPayment.setPaymentStatus(true);
                    System.out.println("Payment method "+newPayment.getName()+" added successfully.");
                    branch.getPaymentList().displayAllPayments();
                }else {
                    System.out.println("Payment method already exists.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Enter a valid index.");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "Error. Please try again.");
            }

        }
    }

    /**
     * remove payment method to the payment list
     * will go through the Branch list then display Branch
     * display Branch's payment list
     * find payment to remove from the branch payment list
     * if it is found in the branch list yet, can remove , otherwise no
     */
    public void removeFromPaymentList() {
        Scanner scanner = new Scanner(System.in);
        int branchChoice;
        int paymentToRemoveChoice;
        Branch branch=null;
        Payment paymentToBeDeleted;
        while (true) {
            System.out.println("Select the branch that you want to remove your payment method: ");
            BranchList.displayBranchNames();
            try {
                branchChoice = scanner.nextInt();
                branch = BranchList.findBranch(branchChoice-1);

                System.out.println("These are the current payment methods:");
                branch.getPaymentList().displayAllPayments();
                paymentToRemoveChoice=scanner.nextInt();
                branch.getPaymentList().removeIndexedPayment(paymentToRemoveChoice-1);

                Payment paymentToRemove = branch.getPaymentList().getPayment(paymentToRemoveChoice-1);
                System.out.println("Payment method "+ paymentToRemove.getName() + " deleted successfully.");

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid input.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Enter a valid index.");
            } catch (Exception e) {
                System.out.println(e.getMessage() + "Error. Please try again.");
            }
        }
    }

}
