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
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the branch index to open: ");
            BranchList.displayBranchNames();
            while (true) {
                try{
                    int choice = sc.nextInt();
                    Branch branch = BranchList.findBranch(choice-1);
                    if (!branch.getStatus())
                        branch.setStatus(true);
                    System.out.println("Branch is opened.");
                    return;
                }
                catch(InputMismatchException e){
                    System.out.println("Branch not found! Enter a valid branch index:");
                    sc.next();
                }
                catch(Exception e) {
                    System.out.println(e.getMessage()+ " Enter a valid branch index:");
                }
            }
    }

    /**
     * Change branch's status to close.
     */
    public void closeBranch(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the branch to close: ");
            BranchList.displayBranchNames();
            while(true) {
                try{
                    int choice = sc.nextInt();
                    Branch branch = BranchList.findBranch(choice-1);
                    if (branch.getStatus())
                        branch.setStatus(false);
                    System.out.println(branch.getName()+" branch is closed.");
                    return;
                }
                catch(InputMismatchException e){
                    System.out.println("Branch not found! Enter a valid branch index:");
                    sc.next();
                }
                catch(Exception e) {
                    System.out.println(e.getMessage()+ " Enter a valid branch index:");
                }
            }
    }

}
