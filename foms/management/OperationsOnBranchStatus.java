package foms.management;

import foms.workers.AdminWorker;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationsOnBranchStatus {
    /**
     * Operations on branch status is performed by admin worker.
     */
    private AdminWorker admin;

    /**
     * Constructor for OperationsOnBranchStatus class
     * @param admin is the admin worker performing the change of branch status.
     */
    public OperationsOnBranchStatus(AdminWorker admin){
        this.admin = admin;
    }

    /**
     * Change branch's status to open.
     */
    public void openBranch(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the branch to open: ");
            OperationsOnBranchList.displayBranchNames();
            int choice = sc.nextInt();
            Branch branch = OperationsOnBranchList.findBranch(choice-1);
            if (!branch.getStatus())
                branch.setStatus(true);
            System.out.println("Branch is opened.");
        }catch(InputMismatchException e){
            System.out.println("input mismatch! enter a valid integer.");
        }
    }

    /**
     * Change branch's status to close.
     */
    public void closeBranch(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the branch to close: ");
            OperationsOnBranchList.displayBranchNames();
            int choice = sc.nextInt();
            Branch branch = OperationsOnBranchList.findBranch(choice-1);
            if (branch.getStatus())
                branch.setStatus(false);
            System.out.println("Branch is closed.");
        }catch(InputMismatchException e){
            System.out.println("input mismatch! enter a valid integer.");
        }
    }

}
