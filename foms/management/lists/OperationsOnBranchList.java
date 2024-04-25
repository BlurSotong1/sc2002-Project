package foms.management.lists;

import foms.management.branch.Branch;
import foms.management.lists.BranchList;
import foms.workers.AdminWorker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OperationsOnBranchList {
    /**
     * operations On branch list is performed by admin worker.
     */
    private AdminWorker admin;

    /**
     * Constructor for OperationsOnBranchList class
     * @param admin is the admin worker performing the operations on branch list.
     */
    public OperationsOnBranchList(AdminWorker admin){
        this.admin = admin;
    }

    /**
     * Add Branch.
     */
    public void addBranch() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String branchName;
            try {
                System.out.print("Enter the new branch name (Enter 0 to exit): ");
                branchName = scanner.nextLine();
                if (branchName.equals("0")) {
                    System.out.println("Returning to previous page..");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong");
                return;
            }


            if (admin.getBranchList().isBranchInBranchList(branchName)!= null) { //if not null, means there is a duplicate
                System.out.println("This branch name already exists in system.");
                continue;
            }

            String location;
            int quota;

            while (true) {
                try {
                    System.out.print("Enter the location of the branch (Enter 0 to exit): ");
                    location = scanner.nextLine();
                    if (location.equals("0")) {
                        System.out.println("Returning to previous page..");
                        return;
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong");
                    return;
                }

                System.out.print("Enter the branch quota (Enter 0 to exit): ");
                while (true) {
                    try {
                        quota = scanner.nextInt();
                        if (quota == 0) {
                            System.out.println("Returning to previous page..");
                            return;
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Enter a valid worker age (number):");
                        scanner.next();
                        continue;
                    }


                    admin.getBranchList().addCreatedBranch(new Branch(branchName, location, quota));
                    return;
                }
            }
        }
    }


    /**
     * Remove Branch.
     */
    public void removeBranch() {
        Scanner scanner = new Scanner(System.in);
        BranchList.displayBranchNames();
        System.out.println("Enter the branch index to remove (Enter 0 to exit): ");
        while(true) {
            try {
                int choice = scanner.nextInt()-1;
                if (choice==-1) {
                    System.out.println("Returning to previous page..");
                    return;
                }
                Branch branch = BranchList.findBranch(choice);
                admin.getBranchList().removeBranchObject(branch);
                return;
            }
            catch (InputMismatchException e) {
                System.out.println("Enter a valid branch index.");
                scanner.next();
            }
            catch (Exception e) {
                System.out.println(e.getMessage()+" Enter a valid branch index.");
            }
        }
    }

}
