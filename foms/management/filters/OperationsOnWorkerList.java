package foms.management.filters;

import java.util.InputMismatchException;
import java.util.Scanner;

import foms.management.lists.AllWorkersList;
import foms.management.lists.BranchList;
import foms.management.branch.Branch;
import foms.workers.AdminWorker;
import foms.workers.*;

public class OperationsOnWorkerList {
    /**
     * Operations on worker list is performed by admin worker.
     */
    private static AdminWorker admin;


    /**
     * Constructor for OperationsOnWorkerList class
     * @param admin is the admin worker performing operations on worker list.
     */
    public OperationsOnWorkerList(AdminWorker admin){
        this.admin = admin;
    }

    /**
     * add worker.
     */
    public void addWorker() {
        Scanner sc = new Scanner(System.in);
        String workerLoginID;
        while (true) {

            try {
                System.out.print("Enter the new worker login ID (Enter 0 to exit): ");
                workerLoginID = sc.nextLine();
                if (workerLoginID.equals("0")) {
                    System.out.println("Returning to previous page..");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Something Went Wrong");
                return;
            }

            if (AllWorkersList.isWorkerInSystem(workerLoginID) != null) { //if not null, means there is a duplicate
                System.out.println("This worker login ID already exists in system.\nReturning to previous page..");
                return;
            }

            String workerName;
            int workerAge;
            char workerGender;
            Branch branch;

            System.out.print("Enter the new worker name (Enter 0 to exit): ");
            while (true) {
                workerName = sc.nextLine();
                if (workerName.equals("0")) {
                    System.out.println("Returning to previous page..");
                    return;
                }

                System.out.print("Enter the new worker age (Enter 0 to exit): ");
                while (true) {
                    try {
                        workerAge = sc.nextInt();
                        if (workerAge == 0) {
                            System.out.println("Returning to previous page..");
                            return;
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.println("Enter a valid worker age (number):");
                        sc.next();
                        continue;
                    }

                    System.out.print("1. Male\n2. Female\nSelect new worker gender (Enter 0 to exit): ");
                    while(true) {
                        try {
                            int choice = sc.nextInt();
                            if (choice == 0) {
                                System.out.println("Returning to previous page..");
                                return;
                            }
                            else if (choice == 1) {
                                workerGender = 'M';
                            }
                            else if (choice == 2) {
                                workerGender = 'F';
                            }
                            else {
                                System.out.println("1. Male\n2. Female\nEnter a valid choice (number) (Enter 0 to exit): ");
                                continue;
                            }
                        }
                        catch (InputMismatchException e){
                            System.out.println("1. Male\n2. Female\nEnter a valid choice (number) (Enter 0 to exit): ");
                            sc.next();
                            continue;
                        }

                        BranchList.displayBranchNames();
                        System.out.println("Select new worker branch (Enter 0 to exit):");
                        while (true) {
                            try {
                                int choice2 = sc.nextInt()-1;
                                if (choice2 == -1) {
                                    System.out.println("Returning to previous page..");
                                    return;
                                }
                                else {
                                    branch = BranchList.findBranch(choice2);
                                }
                            }
                            catch (InputMismatchException e){
                                System.out.println("Enter a valid branch index (Enter 0 to exit): ");
                                sc.next();
                                continue;
                            }
                            catch(Exception e) {
                                System.out.println(e.getMessage()+ " Enter a valid branch index (Enter 0 to exit): ");
                                continue;
                            }

                            System.out.print("1. Manager\n2. Staff\nSelect new worker role (Enter 0 to exit): ");
                            while (true) {
                                try {
                                    int choice3 = sc.nextInt();
                                    if (choice3 == 0) {
                                        System.out.println("Returning to previous page..");
                                        return;
                                    }
                                    else if (choice3 == 1) {
                                        System.out.printf("Please confirm that you want to add %s.\n",workerName);
                                        System.out.println("Enter 1 to add (Enter 0 to exit): ");
                                        while (true) {
                                            String choice4 = sc.next();
                                            if (choice4.equals("1")) {
                                                Worker manager = new ManagerWorker(workerName, workerAge, workerGender, workerLoginID, branch);
                                                branch.getWorkerList().addCreatedWorker(manager);
                                                admin.getAllWorkersList().addCreatedWorker(manager);
                                                System.out.printf("Added %s.\n", workerName);
                                                return;
                                            }
                                            else if (choice4.equals("2")) {
                                                System.out.println("Returning to previous page..");
                                                break;
                                            }
                                            else if (choice4.equals("0")){
                                                return;
                                            }
                                            else {
                                                System.out.println("Enter 1 to add (Enter 0 to exit): ");
                                                continue;
                                            }
                                        }
                                    }
                                    else if (choice3 == 2) {
                                        System.out.printf("Please confirm that you want to add %s.\n",workerName);
                                        System.out.println("Enter 1 to add (Enter 0 to exit): ");
                                        while (true) {
                                            String choice4 = sc.next();
                                            if (choice4.equals("1")) {
                                                Worker staff = new StaffWorker(workerName, workerAge, workerGender, workerLoginID, branch);
                                                branch.getWorkerList().addCreatedWorker(staff);
                                                admin.getAllWorkersList().addCreatedWorker(staff);
                                                System.out.printf("Added %s.\n", workerName);
                                                return;
                                            }
                                            else if (choice4.equals("2")) {
                                                System.out.println("Returning to previous page..");
                                                break;
                                            }
                                            else if (choice4.equals("0")){
                                                return;
                                            }
                                            else {
                                                System.out.println("Enter 1 to add (Enter 0 to exit): ");
                                                continue;
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("1. Manager\n2. Staff\nEnter a valid choice (number) (Enter 0 to exit): ");
                                        continue;
                                    }
                                }
                                catch (InputMismatchException e){
                                    System.out.println("1. Manager\n2. Staff\nEnter a valid choice (number) (Enter 0 to exit): ");
                                    sc.next();
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * display list of workers in the company.
     */
    public void displayWorkerList() {
        admin.getAllWorkersList().displayWorkerListInSystem();

    }
}
