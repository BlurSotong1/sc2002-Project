package foms.management.lists;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import foms.management.branch.Branch;
import foms.workers.AdminWorker;
import foms.workers.*;

public class OperationsOnWorkerList implements Serializable {
    /**
     * Operations on worker list is performed by admin worker.
     */
    private AdminWorker admin;


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
                        System.out.println("Select new worker branch (Enter 0 to exit): ");
                        while (true) {
                            try {
                                int choice2 = sc.nextInt()-1;
                                if (choice2 == -1) {
                                    System.out.println("Returning to previous page..");
                                    return;
                                }
                                else {
                                    branch = BranchList.findBranch(choice2);
                                    if (branch.checkQuota()==false) {
                                        System.out.println("Branch is full.");
                                        System.out.println("Returning to previous page..");
                                        return;
                                    }
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
                                                if (branch.checkCanAddManager()==false) {
                                                    System.out.println("Manager cannot be assigned because quota ratio is full.");
                                                    System.out.printf("Number of staff (excluding Manager): \t%d\n", branch.getNumStaff());
                                                    System.out.printf("Number of manager: \t\t\t%d\n", branch.getNumManager());
                                                    System.out.println("Returning to previous page..");
                                                    return;
                                                }
                                                Worker manager = new ManagerWorker(workerName, workerAge, workerGender, workerLoginID, branch);
                                                branch.getWorkerList().addCreatedWorker(manager);
                                                admin.getAllWorkersList().addCreatedWorker(manager);
                                                branch.setNumManager(branch.getNumManager()+1);
                                                System.out.printf("Added %s.\n", workerName);

                                                if (branch.checkRatio()==false){
                                                    System.out.printf("%s Branch Manpower:\n", branch.getName());
                                                    System.out.printf("Number of staff (excluding Manager): \t%d\n", branch.getNumStaff());
                                                    System.out.printf("Number of manager: \t\t\t%d\n", branch.getNumManager());
                                                    System.out.println("Note that quota ratio is not met.");
                                                    return;
                                                }
                                                else {
                                                    System.out.printf("%s Branch Manpower:\n", branch.getName());
                                                    System.out.printf("Number of staff (excluding Manager): \t%d\n", branch.getNumStaff());
                                                    System.out.printf("Number of manager: \t\t\t%d\n", branch.getNumManager());
                                                    System.out.println("Quota ratio is met.");
                                                    return;
                                                }
                                            }
                                            else if (choice4.equals("2")) {
                                                System.out.println("Returning to previous page..");
                                                return;
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
                                                branch.setNumStaff(branch.getNumStaff()+1);
                                                System.out.printf("Added %s.\n", workerName);

                                                if (branch.checkRatio()==false){
                                                    System.out.printf("%s Branch Manpower:\n", branch.getName());
                                                    System.out.printf("Number of staff (excluding Manager): \t%d\n", branch.getNumStaff());
                                                    System.out.printf("Number of manager: \t\t\t%d\n", branch.getNumManager());
                                                    System.out.println("Note that quota is not met.");
                                                    return;
                                                }
                                                else {
                                                    System.out.printf("%s Branch Manpower:\n", branch.getName());
                                                    System.out.printf("Number of staff (excluding Manager): \t%d\n", branch.getNumStaff());
                                                    System.out.printf("Number of manager: \t\t\t%d\n", branch.getNumManager());
                                                    System.out.println("Quota is met.");
                                                    return;
                                                }

                                            }
                                            else if (choice4.equals("2")) {
                                                System.out.println("Returning to previous page..");
                                                return;
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
     * remove worker.
     */
    public void removeWorker() {
        Scanner scanner = new Scanner(System.in);
        int i=0;
        System.out.println("List of workers:");
        for(Worker worker : admin.getAllWorkersList().getWorkerList()) {
            if (worker instanceof AdminWorker)
                continue;
            System.out.printf("%d: %s\n", i+1, worker.toString());
            i++;
        }

        System.out.println("Enter the worker index to remove (Enter 0 to exit): ");
        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice==0) {
                    System.out.println("Returning to previous page..");
                    return;
                }
                Worker worker = admin.getAllWorkersList().findWorker(choice);
                System.out.printf("Please confirm that you want to remove %s.\n",worker.getName());
                System.out.println("Enter 1 to remove (Enter 0 to exit): ");
                while (true) {
                    String choice2 = scanner.next();
                    if (choice2.equals("1")) {
                        admin.getAllWorkersList().removeWorkerObject(worker);
                        if (worker.getRole()=='S') {
                            ((StaffWorker)worker).getBranch().setNumStaff(((StaffWorker)worker).getBranch().getNumStaff()-1);
                            ((StaffWorker)worker).getBranch().getWorkerList().removeWorkerObject(worker);
                        }
                        if (worker.getRole()=='M') {
                            ((ManagerWorker)worker).getBranch().setNumManager(((ManagerWorker)worker).getBranch().getNumManager()-1);
                            ((ManagerWorker)worker).getBranch().getWorkerList().removeWorkerObject(worker);
                        }
                        System.out.printf("Removed %s.\n", worker.getName());
                        if (((StaffWorker)worker).getBranch().checkRatio()==false){
                            System.out.printf("%s Branch Manpower:\n", ((StaffWorker)worker).getBranch().getName());
                            System.out.printf("Number of staff (excluding Manager): \t%d\n", ((StaffWorker)worker).getBranch().getNumStaff());
                            System.out.printf("Number of manager: \t\t\t%d\n", ((StaffWorker)worker).getBranch().getNumManager());
                            System.out.println("Note that quota ratio is not met.");
                            return;
                        }
                        else {
                            System.out.printf("%s Branch Manpower:\n", ((StaffWorker)worker).getBranch().getName());
                            System.out.printf("Number of staff (excluding Manager): \t%d\n", ((StaffWorker)worker).getBranch().getNumStaff());
                            System.out.printf("Number of manager: \t\t\t%d\n", ((StaffWorker)worker).getBranch().getNumManager());
                            System.out.println("Quota ratio is met.");
                            return;
                        }

                    }
                    else if (choice2.equals("0")) {
                        System.out.println("Did not remove worker.\nReturning to previous page..");
                        return;
                    }
                    else {
                        System.out.println("Enter 1 to remove (Enter 0 to exit): ");
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Enter a valid worker index.");
                scanner.next();
                continue;
            }
            catch (Exception e) {
                System.out.println(e.getMessage()+" Enter a valid worker index.");
                continue;
            }
        }

    }

    /**
     * display list of workers in the company.
     */
    public void displayWorkerList() {
        admin.getAllWorkersList().displayWorkerListInSystem();

    }

    /**
     * Promote a staff to Branch Manager
     */
    public void promoteToManager() {
        Scanner scanner = new Scanner(System.in);
        int i=0;
        System.out.println("List of workers:");
        for(Worker worker : admin.getAllWorkersList().getWorkerList()) {
            if (worker instanceof AdminWorker)
                continue;
            System.out.printf("%d: %s\n", i+1, worker.toString());
            i++;
        }

        System.out.println("Enter the staff worker index to make manager (Enter 0 to exit): ");
        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice==0) {
                    System.out.println("Returning to previous page..");
                    return;
                }
                Worker worker = admin.getAllWorkersList().findWorker(choice);
                if (worker instanceof ManagerWorker) {
                    System.out.println(worker.getName()+" is already a manager.");
                    System.out.println("Enter the staff worker index to make manager (Enter 0 to exit): ");
                    continue;
                }
                else if (worker instanceof AdminWorker) {
                    System.out.println(worker.getName()+" is an admin.");
                    System.out.println("Enter the staff worker index to make manager (Enter 0 to exit): ");
                    continue;
                }
                else {
                    Branch branch = ((StaffWorker)worker).getBranch();
                    ((StaffWorker)worker).getBranch().setNumStaff(((StaffWorker)worker).getBranch().getNumStaff()-1);
                    if (branch.checkCanAddManager()==false) {
                        ((StaffWorker)worker).getBranch().setNumStaff(((StaffWorker)worker).getBranch().getNumStaff()+1);
                        System.out.println("Staff worker cannot be promoted because quota ratio is full.");
                        System.out.printf("Number of staff (excluding Manager): \t%d\n", branch.getNumStaff());
                        System.out.printf("Number of manager: \t\t\t%d\n", branch.getNumManager());
                        System.out.println("Returning to previous page..");
                        return;
                    }

                    String name = worker.getName();
                    int age = worker.getAge();
                    char gender = worker.getGender();
                    String loginID = worker.getLoginID();
                    String loginPassword = worker.getLoginPassword();
                    ManagerWorker newManager = new ManagerWorker(name, age, gender, loginID, branch);
                    newManager.setLoginPassword(loginPassword);

                    admin.getAllWorkersList().removeWorkerObject(worker);
                    admin.getAllWorkersList().addCreatedWorker(newManager);

                    ((StaffWorker)worker).getBranch().getWorkerList().removeWorkerObject(worker);
                    ((StaffWorker)worker).getBranch().getWorkerList().addCreatedWorker(newManager);

                    ((StaffWorker)worker).getBranch().setNumManager(((StaffWorker)worker).getBranch().getNumManager()+1);

                    if (((StaffWorker)worker).getBranch().checkRatio()==false){
                        System.out.printf("%s Branch Manpower:\n", ((StaffWorker)worker).getBranch().getName());
                        System.out.printf("Number of staff (excluding Manager): \t%d\n", ((StaffWorker)worker).getBranch().getNumStaff());
                        System.out.printf("Number of manager: \t\t\t%d\n", ((StaffWorker)worker).getBranch().getNumManager());
                        System.out.println("Note that quota ratio is not met.");
                        return;
                    }
                    else {
                        System.out.printf("%s Branch Manpower:\n", ((StaffWorker)worker).getBranch().getName());
                        System.out.printf("Number of staff (excluding Manager): \t%d\n", ((StaffWorker)worker).getBranch().getNumStaff());
                        System.out.printf("Number of manager: \t\t\t%d\n", ((StaffWorker)worker).getBranch().getNumManager());
                        System.out.println("Quota ratio is met.");
                        return;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Enter a valid worker index.");
                scanner.next();
                continue;
            }
            catch (Exception e) {
                System.out.println(e.getMessage()+" Enter a valid worker index.");
                continue;
            }

        }
    }

}


